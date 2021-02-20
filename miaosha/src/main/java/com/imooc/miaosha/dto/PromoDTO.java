package com.imooc.miaosha.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author DateBro
 * @Date 2021/2/18 16:03
 */
@Data
public class PromoDTO implements Serializable {

    private static final long serialVersionUID = -1510806108543929310L;

    private Integer promoId;

    private String promoName;

    private Integer productId;

    private BigDecimal promoProductPrice;

    private Date promoStartTime;

    private Date promoEndTime;

    /**
     * 秒杀活动状态，1表示为开始，2表示进行中，3表示已结束
     */
    private Integer promoStatus;
}
