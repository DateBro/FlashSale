package com.imooc.miaosha.enums;

import lombok.Getter;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:25
 */
@Getter
public enum ResultEnum {
    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_PASSWORD_ERROR(20002, "用户密码错误"),
    USER_NOT_LOGIN_ERROR(20003, "用户尚未登录，请先登录"),

    //30000开头为交易信息错误定义
    STOCK_NOT_ENOUGH(30001, "商品库存不足"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
