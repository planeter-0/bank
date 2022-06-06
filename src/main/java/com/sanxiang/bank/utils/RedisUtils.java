package com.sanxiang.bank.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

public class RedisUtils {
    @Resource
    public static RedisTemplate<String, Object> redisTemplate;
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
}
