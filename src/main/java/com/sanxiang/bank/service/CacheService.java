package com.sanxiang.bank.service;


public interface CacheService {

    void cachePut(String key, Object toBeCached, long ttlMinutes);

    void cachePut(String key, Object toBeCached);

    <T> T cacheGet(String key, Class<T> type);
}
