package com.imooc.miaosha.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:32
 */
@Data
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 2078252690269569352L;

    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productSales;

    private Integer stock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    /**
     * 如果promoDTO非空说明有活动信息
     */
    private PromoDTO promoDTO;
}
