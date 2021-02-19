package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dataobject.PromoInfo;
import com.imooc.miaosha.dto.PromoDTO;
import com.imooc.miaosha.repository.PromoInfoRepository;
import com.imooc.miaosha.service.PromoService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public PromoDTO getPromoByProductId(Integer productId) {
        PromoInfo promoInfo = promoInfoRepository.findByProductId(productId);
        PromoDTO promoDTO = new PromoDTO();
        if (promoInfo == null) {
            promoDTO = null;
        } else {
            BeanUtils.copyProperties(promoInfo, promoDTO);
            // 判断当前时间是否秒杀活动即将开始或正在进行
            DateTime promoStartTime = new DateTime(promoInfo.getPromoStartTime());
            DateTime promoEndTime = new DateTime(promoInfo.getPromoEndTime());
            if (promoStartTime.isAfterNow()) {
                promoDTO.setPromoStatus(1);
            } else if (promoEndTime.isBeforeNow()) {
                promoDTO.setPromoStatus(3);
            } else {
                promoDTO.setPromoStatus(2);
            }
        }

        return promoDTO;
    }
}
