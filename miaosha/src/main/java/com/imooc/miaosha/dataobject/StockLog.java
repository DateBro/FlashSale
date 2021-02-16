package com.imooc.miaosha.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author DateBro
 * @Date 2021/2/16 15:50
 */
@Entity
@Data
public class StockLog {
    @Id
    private String stockId;

    private Integer productId;

    private Integer quantity;

    private Integer status;
}
