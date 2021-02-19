package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.ProductStock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:40
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class ProductStockRepositoryTest {

    @Autowired
    private ProductStockRepository stockRepository;

    @Test
    void save() {
        ProductStock productStock = new ProductStock();
        productStock.setProductId(100);
        productStock.setStock(100);
        ProductStock result = stockRepository.save(productStock);
        assertNotNull(result);
        log.info("商品库存信息为：" + result);
    }

    @Test
    void getProductList() {
        List<ProductStock> productStockList = stockRepository.findAll();
        assertNotEquals(0, productStockList.size());
        for (int i = 0; i < productStockList.size(); i++) {
            log.info("第" + i + "个商品库存信息为：" + productStockList.get(i));
        }
    }

    @Test
    void findByProductId() {
        Integer productId = 3;
        ProductStock productStock = stockRepository.findByProductId(productId);
        assertNotNull(productStock);
        log.info("" + productStock);
    }

}