package com.imooc.miaosha.viewobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author DateBro
 * @Date 2021/2/18 10:55
 */
@Data
public class OrderVO {
    private String orderId;

    private Integer buyerId;

    private Integer productId;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private BigDecimal orderAmount;

    /**
     * 订单状态，默认为0新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态，默认为0未支付
     */
    private Integer payStatus;
}
