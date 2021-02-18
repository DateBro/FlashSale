package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author DateBro
 * @Date 2021/2/17 22:39
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class OrderInfoRepositoryTest {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Test
    void findAllByBuyerId() {
        Integer buyerId = 1;
        List<OrderInfo> orderInfoList = orderInfoRepository.findAllByBuyerId(buyerId);
        assertNotNull(orderInfoList);
        assertNotEquals(0, orderInfoList.size());
        for (int i = 0; i < orderInfoList.size(); i++) {
            log.info("订单详情为：" + orderInfoList.get(i));
        }
    }

    @Test
    void save() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("1111");
        orderInfo.setBuyerId(1);
        orderInfo.setProductId(1);
        orderInfo.setProductPrice(new BigDecimal(new Double(2000.00)));
        orderInfo.setProductQuantity(3);
        orderInfo.setOrderAmount(new BigDecimal(new Double(6000.00)));

        OrderInfo result = orderInfoRepository.save(orderInfo);
        assertNotNull(result);
        log.info("存入的订单详情为：" + result);
    }
}