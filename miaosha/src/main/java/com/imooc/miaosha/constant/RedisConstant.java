package com.imooc.miaosha.constant;

/**
 * @Author DateBro
 * @Date 2021/2/20 10:26
 */
public interface RedisConstant {

    Integer EXPIRE = 7200;

    String PRODUCT_DETAIL_PREFIX = "product_%s";

    // 过期时间10分钟
    Integer PRODUCT_DETAIL_EXPIRE = 600;

    String BUYER_DETAIL_VALIDATE_PREFIX = "buyer_validate_%s";

    Integer BUYER_DETAIL_VALIDATE_EXPIRE = 600;

    String PRODUCT_DETAIL_VALIDATE_PREFIX = "product_validate_%s";

    Integer PRODUCT_DETAIL_VALIDATE_EXPIRE = 600;
}
