package com.imooc.miaosha.enums;

import lombok.Getter;

/**
 * @Author DateBro
 * @Date 2021/2/21 13:56
 */
@Getter
public enum StockLogStatusEnum {
    INIT(1, "初始化"),
    COMMIT(2, "已下单"),
    ROLLBACK(3, "已回滚"),
    ;

    private Integer status;

    private String msg;

    StockLogStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
