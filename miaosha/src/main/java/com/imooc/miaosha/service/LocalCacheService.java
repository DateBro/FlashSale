package com.imooc.miaosha.service;

/**
 * @Author DateBro
 * @Date 2021/2/20 14:21
 */
public interface LocalCacheService {

    void set(String key, Object value);

    Object get(String key);
}
