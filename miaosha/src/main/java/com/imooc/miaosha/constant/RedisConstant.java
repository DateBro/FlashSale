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

    String PROMO_PRODUCT_STOCK_PREFIX = "promo_product_stock_%s";

    String PRODUCT_STOCK_INVALID_PREFIX = "product_stock_invalid_%s";

    String PROMO_PRODUCT_TOKEN_PATTER = "promoToken_product_%s_promo_%s_buyer_%s";

    Integer PROMO_PRODUCT_TOKEN_EXPIRE = 300;

    String PROMO_PRODUCT_TOKEN_LATCH_PATTERN = "promoProductTokenLatch_product_%s_promo_%s";

    String VERIFYCODE_BUYER_PREFIX = "verifycode_buyer_%s";

    Integer VERIFYCODE_BUYER_EXPIRE = 300;
}
