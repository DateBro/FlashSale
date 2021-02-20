package com.imooc.miaosha.utils;

import java.util.Random;

/**
 * @Author DateBro
 * @Date 2021/2/20 17:54
 */
public class KeyUtil {

    /**生成唯一主键，通过当前时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
