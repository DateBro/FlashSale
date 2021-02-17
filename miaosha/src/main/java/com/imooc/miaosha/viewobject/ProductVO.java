package com.imooc.miaosha.viewobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:46
 */
@Data
public class ProductVO {

    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productSales;

    private Integer stock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;
}
