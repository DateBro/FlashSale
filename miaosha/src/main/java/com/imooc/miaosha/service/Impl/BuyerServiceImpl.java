package com.imooc.miaosha.service.Impl;

import com.alibaba.druid.util.StringUtils;
import com.imooc.miaosha.dataobject.BuyerInfo;
import com.imooc.miaosha.dataobject.BuyerPassword;
import com.imooc.miaosha.dto.BuyerDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.repository.BuyerInfoRepository;
import com.imooc.miaosha.repository.BuyerPasswordRepository;
import com.imooc.miaosha.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author DateBro
 * @Date 2021/2/17 12:11
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    BuyerInfoRepository buyerInfoRepository;

    @Autowired
    BuyerPasswordRepository buyerPasswordRepository;

    @Override
    @Transactional
    public BuyerInfo register(BuyerDTO buyerDTO) {
        // 首先进行参数校验
        if (buyerDTO == null) {
            log.error("【买家注册】buyerDTO为空");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        // 将DTO转换成dataobject
        BuyerInfo buyerInfo = new BuyerInfo();
        BeanUtils.copyProperties(buyerDTO, buyerInfo);
        BuyerInfo buyerInfoResult = buyerInfoRepository.save(buyerInfo);

        buyerDTO.setBuyerId(buyerInfoResult.getBuyerId());

        BuyerPassword buyerPassword = new BuyerPassword();
        buyerPassword.setBuyerId(buyerDTO.getBuyerId());
        buyerPassword.setEncryptPassword(buyerDTO.getEncryptPassword());
        buyerPasswordRepository.save(buyerPassword);
        return buyerInfo;
    }

    @Override
    public BuyerDTO validateLogin(String telephone, String password) {
        if (StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)) {
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        BuyerInfo buyerInfo = buyerInfoRepository.findByTelephone(telephone);
        if (buyerInfo == null) {
            throw new MiaoshaException(ResultEnum.USER_NOT_EXIST);
        }

        BuyerPassword buyerPassword = buyerPasswordRepository.findByBuyerId(buyerInfo.getBuyerId());
        if (buyerPassword == null) {
            throw new MiaoshaException(ResultEnum.USER_NOT_EXIST);
        }

        // 检查密码是否正确
        if (!StringUtils.equals(password, buyerPassword.getEncryptPassword())) {
            throw new MiaoshaException(ResultEnum.USER_PASSWORD_ERROR);
        }
        BuyerDTO buyerDTO = new BuyerDTO();
        BeanUtils.copyProperties(buyerInfo, buyerDTO);
        buyerDTO.setEncryptPassword(buyerPassword.getEncryptPassword());

        return buyerDTO;
    }
}