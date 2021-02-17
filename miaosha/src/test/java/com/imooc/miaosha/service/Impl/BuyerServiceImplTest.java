package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dataobject.BuyerInfo;
import com.imooc.miaosha.dto.BuyerDTO;
import com.imooc.miaosha.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author DateBro
 * @Date 2021/2/17 15:23
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class BuyerServiceImplTest {

    @Autowired
    private BuyerServiceImpl buyerService;

    @Test
    void register() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        BuyerDTO buyerDTO = new BuyerDTO();
        buyerDTO.setUsername("RegisterTest");
        buyerDTO.setAge(18);
        buyerDTO.setGender(1);
        buyerDTO.setTelephone("12345678123");
        buyerDTO.setEncryptPassword(PasswordUtil.EncodeByMd5("123456"));
        buyerDTO.setOtpCode("1234");
        buyerDTO.setRegisterMode("byphone");

        BuyerInfo result = buyerService.register(buyerDTO);
        assertNotNull(result);
    }

    @Test
    void validateLogin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String telephone = "12345678123";
        String password = "123456";
        BuyerDTO buyerDTO = buyerService.validateLogin(telephone, PasswordUtil.EncodeByMd5(password));
        assertNotNull(buyerDTO);
    }

}