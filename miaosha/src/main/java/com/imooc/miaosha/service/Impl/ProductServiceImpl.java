package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dataobject.ProductInfo;
import com.imooc.miaosha.dataobject.ProductStock;
import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.repository.ProductInfoRepository;
import com.imooc.miaosha.repository.ProductStockRepository;
import com.imooc.miaosha.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:33
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductStockRepository stockRepository;

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
}
