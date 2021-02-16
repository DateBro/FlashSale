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
 * @Date 2021/2/16 15:47
 */
@Entity
@Data
public class PromoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer promoId;

    private String promoName;

    private Integer productId;

    private BigDecimal promoProductPrice;

    private Date promoStartTime;

    private Date promoEndTime;
}
