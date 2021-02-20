package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.constant.RedisConstant;
import com.imooc.miaosha.dataobject.ProductInfo;
import com.imooc.miaosha.dataobject.ProductStock;
import com.imooc.miaosha.dto.OrderDTO;
import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.dto.PromoDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.repository.ProductInfoRepository;
import com.imooc.miaosha.repository.ProductStockRepository;
import com.imooc.miaosha.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:33
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductStockRepository stockRepository;

    @Autowired
    private PromoServiceImpl promoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        // 对参数进行检查
        if (productDTO == null) {
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        // 转换为dataobject
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productDTO, productInfo);

        // 将商品信息存入数据库
        ProductInfo savedProductInfo = productInfoRepository.save(productInfo);
        productDTO.setProductId(savedProductInfo.getProductId());

        // 将商品库存信息存入数据库
        ProductStock productStock = new ProductStock();
        productStock.setProductId(productDTO.getProductId());
        productStock.setStock(productDTO.getStock());
        stockRepository.save(productStock);

        return productDTO;
    }

    @Override
    public List<ProductDTO> getProductList() {
        List<ProductInfo> productInfoList = productInfoRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(productInfo, productDTO);
            ProductStock stock = stockRepository.findByProductId(productInfo.getProductId());
            productDTO.setStock(stock.getStock());
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public ProductDTO getProductDetail(Integer productId) {
        ProductInfo productInfo = productInfoRepository.getOne(productId);
        if (productInfo == null) {
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        ProductStock productStock = stockRepository.findByProductId(productId);
        if (productStock == null) {
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productInfo, productDTO);
        productDTO.setStock(productStock.getStock());

        // 获取商品活动信息
        PromoDTO promoDTO = promoService.getPromoByProductId(productId);
        if (promoDTO != null && promoDTO.getPromoStatus().intValue() != 3) {
            productDTO.setPromoDTO(promoDTO);
        }

        return productDTO;
    }

    @Override
    public ProductDTO getProductDetailInCache(Integer productId) {
        ProductDTO productDTO = (ProductDTO) redisTemplate.opsForValue().get(String.format(RedisConstant.PRODUCT_DETAIL_VALIDATE_PREFIX, productId));
        if (productDTO == null) {
            productDTO = this.getProductDetail(productId);
            redisTemplate.opsForValue().set(String.format(RedisConstant.PRODUCT_DETAIL_VALIDATE_PREFIX, productId), productDTO, RedisConstant.PRODUCT_DETAIL_VALIDATE_EXPIRE, TimeUnit.SECONDS);
        }
        return productDTO;
    }

    @Override
    @Transactional
    public void decreaseStock(OrderDTO orderDTO) {
        ProductStock productStock = stockRepository.findByProductId(orderDTO.getProductId());
        if (productStock == null) {
            log.error("【扣减库存】商品库存信息不存在");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        Integer resultStock = productStock.getStock() - orderDTO.getProductQuantity();
        if (resultStock < 0) {
            throw new MiaoshaException(ResultEnum.STOCK_NOT_ENOUGH);
        }
        productStock.setStock(resultStock);
        stockRepository.save(productStock);
    }

    @Override
    @Transactional
    public void increaseSales(OrderDTO orderDTO) {
        ProductInfo productInfo = productInfoRepository.getOne(orderDTO.getProductId());
        if (productInfo == null) {
            log.error("【修改销量】商品不存在");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        Integer resultSales = productInfo.getProductSales() + orderDTO.getProductQuantity();
        productInfo.setProductSales(resultSales);
        productInfoRepository.save(productInfo);
    }
}
