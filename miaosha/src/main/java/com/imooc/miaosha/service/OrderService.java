package com.imooc.miaosha.service;

import com.imooc.miaosha.dto.OrderDTO;

/**
 * @Author DateBro
 * @Date 2021/2/18 10:33
 */
public interface OrderService {
    OrderDTO create(OrderDTO orderDTO, Integer promoId);
}
