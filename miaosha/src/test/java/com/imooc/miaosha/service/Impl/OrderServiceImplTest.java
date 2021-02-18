package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author DateBro
 * @Date 2021/2/18 12:10
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerId(1);
        orderDTO.setProductId(1);
        orderDTO.setProductQuantity(2);
        OrderDTO result = orderService.create(orderDTO);
        assertNotNull(result);
        log.info("创建的订单信息为：" + result);
    }
}