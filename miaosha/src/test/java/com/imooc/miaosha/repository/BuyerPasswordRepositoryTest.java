package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.BuyerPassword;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author DateBro
 * @Date 2021/2/17 16:02
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class BuyerPasswordRepositoryTest {

    @Autowired
    private BuyerPasswordRepository repository;

    @Test
    void findByBuyerId() {
        Integer buyerId = 4;
        BuyerPassword result = repository.findByBuyerId(buyerId);
        assertNotNull(result);
        log.info("用户的密码为: " + result.getEncryptPassword());
    }
}