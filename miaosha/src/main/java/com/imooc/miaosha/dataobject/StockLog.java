package com.imooc.miaosha.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author DateBro
 * @Date 2021/2/16 15:50
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class StockLog {
    @Id
    private String stockLogId;

    private Integer productId;

    private Integer quantity;

    /**
     * 默认为0，1为未下单，2为已下单，3为已回滚
     */
    private Integer status;
}
