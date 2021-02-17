package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author DateBro
 * @Date 2021/2/17 17:51
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    void findByProductId() {
        Integer productId = 1;
        Optional<ProductInfo> result = productInfoRepository.findById(productId);
        assertEquals(true, result.isPresent());
        log.info("查到的商品信息为：" + result.get());
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("iPhone8");
        productInfo.setProductDescription("非常好用的一款手机");
        productInfo.setProductPrice(new BigDecimal(new Double(2000.00)));
        productInfo.setProductIcon("https://img.pconline.com.cn/images/product/6272/627291/iPhone8-X2_sn8.jpg");

        ProductInfo result = productInfoRepository.save(productInfo);
        assertNotNull(result);
    }

}