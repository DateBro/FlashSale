package com.imooc.miaosha.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author DateBro
 * @Date 2021/2/16 15:42
 */
@Entity
@Data
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productSales;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;
}
