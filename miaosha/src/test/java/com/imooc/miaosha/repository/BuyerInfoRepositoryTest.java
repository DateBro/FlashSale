package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.BuyerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author DateBro
 * @Date 2021/2/17 15:03
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class BuyerInfoRepositoryTest {

    @Autowired
    private BuyerInfoRepository buyerInfoRepository;

    @Test
    void findByTelephone() {
        String telephone = "18512345678";
        BuyerInfo result = buyerInfoRepository.findByTelephone(telephone);
        log.info("【查到的用户信息为】" + result);
        Assertions.assertNotNull(result);
    }

    @Test
    void save() {
        BuyerInfo buyerInfo = new BuyerInfo();
        buyerInfo.setUsername("DateBro");
        buyerInfo.setAge(18);
        buyerInfo.setGender(1);
        buyerInfo.setRegisterMode("byphone");
        buyerInfo.setTelephone("18512345678");
        BuyerInfo result = buyerInfoRepository.save(buyerInfo);
        Assertions.assertNotEquals(null, result);
    }
}