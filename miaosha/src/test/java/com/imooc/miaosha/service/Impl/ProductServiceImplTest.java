package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

/**
 * @Author DateBro
 * @Date 2021/2/17 19:02
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    void create() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("华为Mate30");
        productDTO.setProductPrice(new BigDecimal(new Double(1600.00)));
        productDTO.setStock(100);
        productDTO.setProductIcon("https://2a.zol-img.com.cn/product/201_120x90/592/cewznHYD4wOFs.jpg");
        productDTO.setProductDescription("一款好用的安卓手机");

        ProductDTO result = productService.create(productDTO);
        Assertions.assertNotNull(result);
        log.info("存入的商品信息为：" + result);
    }
}