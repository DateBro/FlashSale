package com.imooc.miaosha.constant;

/**
 * @Author DateBro
 * @Date 2021/2/20 10:26
 */
public interface RedisConstant {
    // 过期时间 2小时
    Integer EXPIRE = 7200;

    String PRODUCT_PREFIX = "product_%s";

    Integer PRODUCT_DETAIL_EXPIRE = 600;
}