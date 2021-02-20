package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.constant.RedisConstant;
import com.imooc.miaosha.converter.PromoInfo2PromoDTOConverter;
import com.imooc.miaosha.dataobject.PromoInfo;
import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.dto.PromoDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.repository.PromoInfoRepository;
import com.imooc.miaosha.service.PromoService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author DateBro
 * @Date 2021/2/18 16:16
 */
@Service
@Slf4j
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoInfoRepository promoInfoRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PromoDTO getPromoByProductId(Integer productId) {
        PromoInfo promoInfo = promoInfoRepository.findByProductId(productId);
        PromoDTO promoDTO = new PromoDTO();
        if (promoInfo == null) {
            promoDTO = null;
        } else {
            promoDTO = PromoInfo2PromoDTOConverter.convert(promoInfo);
        }

        return promoDTO;
    }

    /**
     * 活动发布同步库存进缓存
     * @param promoId
     * @return
     */
    @Override
    public PromoDTO publishPromo(Integer promoId) {
        if (promoId == null) {
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        PromoInfo promoInfo = promoInfoRepository.getOne(promoId);
        if (promoInfo == null) {
            throw new MiaoshaException(ResultEnum.PROMO_NOT_EXIST);
        }
        ProductDTO productDTO = productService.getProductDetail(promoInfo.getProductId());
        // 假设这段时间内stock不会变化
        redisTemplate.opsForValue().set(String.format(RedisConstant.PROMO_PRODUCT_STOCK_PREFIX, productDTO.getProductId()), productDTO.getStock());

        PromoDTO promoDTO = PromoInfo2PromoDTOConverter.convert(promoInfo);

        return promoDTO;
    }
}
