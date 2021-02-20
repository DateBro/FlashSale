package com.imooc.miaosha.service;

import com.imooc.miaosha.dataobject.BuyerInfo;
import com.imooc.miaosha.dto.BuyerDTO;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:08
 */
public interface BuyerService {

    BuyerDTO getBuyerDetailById(Integer buyerId);

    BuyerDTO getBuyerDetailByIdInCache(Integer buyerId);

    BuyerInfo register(BuyerDTO buyerDTO);

    BuyerDTO validateLogin(String telephone, String password);

}
