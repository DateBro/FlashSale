package com.imooc.miaosha.dto;

import lombok.Data;

/**
 * @Author DateBro
 * @Date 2021/2/21 13:27
 */
@Data
public class StockLogDTO {

    private String stockLogId;

    private Integer productId;

    private Integer quantity;

    private Integer status;
}
