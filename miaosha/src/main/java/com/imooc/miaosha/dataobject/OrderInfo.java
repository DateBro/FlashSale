package com.imooc.miaosha.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author DateBro
 * @Date 2021/2/16 15:36
 */
@Entity
@Data
public class OrderInfo {
    @Id
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

    private Date createTime;

    private Date updateTime;
}
