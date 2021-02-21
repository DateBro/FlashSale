package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dto.OrderDTO;
import com.imooc.miaosha.dto.StockLogDTO;
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

    @Autowired
    private StockLogServiceImpl stockLogService;

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerId(4);
        Integer productId = 1;
        Integer productQuantity = 2;
        orderDTO.setProductId(productId);
        orderDTO.setProductQuantity(productQuantity);
        StockLogDTO stockLogDTO = stockLogService.initStockLog(productId, productQuantity);
        OrderDTO result = orderService.create(orderDTO, null, stockLogDTO);
        assertNotNull(result);
        log.info("创建的订单信息为：" + result);
    }
}