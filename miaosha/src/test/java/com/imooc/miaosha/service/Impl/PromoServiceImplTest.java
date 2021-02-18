package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.dto.PromoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author DateBro
 * @Date 2021/2/18 16:35
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class PromoServiceImplTest {

    @Autowired
    private PromoServiceImpl promoService;

    @Test
    void getPromoByProductId() {
        Integer productId = 1;
        PromoDTO promoDTO = promoService.getPromoByProductId(productId);
        assertNotNull(promoDTO);
        log.info("查到的活动信息为：" + promoDTO);
    }
}