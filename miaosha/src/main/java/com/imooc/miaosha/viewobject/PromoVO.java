package com.imooc.miaosha.viewobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author DateBro
 * @Date 2021/2/20 20:51
 */
@Data
public class PromoVO {
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
