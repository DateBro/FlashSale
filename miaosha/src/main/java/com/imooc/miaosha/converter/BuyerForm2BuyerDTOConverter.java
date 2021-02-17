package com.imooc.miaosha.converter;

import com.imooc.miaosha.dto.BuyerDTO;
import com.imooc.miaosha.form.BuyerForm;
import com.imooc.miaosha.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author DateBro
 * @Date 2021/2/17 12:52
 */
@Slf4j
public class BuyerForm2BuyerDTOConverter {

    public static BuyerDTO convert(BuyerForm buyerForm) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        BuyerDTO buyerDTO = new BuyerDTO();
        buyerDTO.setTelephone(buyerForm.getTelephone());
        buyerDTO.setUsername(buyerForm.getUsername());
        buyerDTO.setOtpCode(buyerForm.getOtpCode());
        buyerDTO.setGender(buyerForm.getGender());
        buyerDTO.setAge(buyerForm.getAge());
        buyerDTO.setRegisterMode("byphone");
        buyerDTO.setEncryptPassword(PasswordUtil.EncodeByMd5(buyerForm.getPassword()));
        return buyerDTO;
    }
}
