package com.imooc.miaosha.service.Impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imooc.miaosha.service.LocalCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Author DateBro
 * @Date 2021/2/20 14:22
 */
@Service
@Slf4j
public class LocalCacheServiceImpl implements LocalCacheService {

    private Cache<String, Object> localCache = null;

    @PostConstruct
    void init() {
        localCache = CacheBuilder.newBuilder()
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存中最大可以存储100个KEY,超过100个之后会按照LRU的策略移除缓存项
                .maximumSize(100)
                //设置写缓存后多少秒过期
                .expireAfterWrite(60, TimeUnit.SECONDS).build();
    }

    @Override
    public void set(String key, Object value) {
        localCache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return localCache.getIfPresent(key);
    }
}
