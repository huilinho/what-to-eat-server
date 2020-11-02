package com.what.to.eat.server.cache.impl;


import com.what.to.eat.server.cache.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CacheManagerRedisImpl implements CacheManager {

    private static final int DEFAULT_EXPIRE_SECONDS = 60 * 60 * 24 * 7; // 默认过期时间，7天

    @Autowired
    private RedisTemplate<String, Object> coreRedisTemplate;

    @Override
    public void deleteFromCache(String key) {
        try {
            coreRedisTemplate.delete(key);
        } catch (Throwable t) {
            log.error("delete key: {}", key, t);
        }
    }

    @Override
    public void setToCache(String key, int exp, Object obj) {
        //coreRedisTemplate.opsForValue().set(key, obj);
        this.set(key, obj, exp);
    }

    @Override
    public Object getFromCache(String key) {
        return coreRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean hasKey(String key) {
        return coreRedisTemplate.hasKey(key);
    }


    @Override
    public void set(String key, Object value, int expireSeconds) {
        coreRedisTemplate.opsForValue().set(key, value, expireSeconds, TimeUnit.SECONDS);
    }


    @Override
    public void expire(String key, long seconds) {
        coreRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
}
