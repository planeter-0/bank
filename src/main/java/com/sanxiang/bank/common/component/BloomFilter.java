package com.sanxiang.bank.common.component;

//import org.redisson.Redisson;
//import org.redisson.api.RBloomFilter;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//TODO RedissonClient连接不上
//@Component
//public class BloomFilter {
//    @Resource
//    RedissonClient redissonClient;
//    public RBloomFilter<String> getBloomFilter(String name,long size,double v) {
//        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(name);
//        bloomFilter.tryInit(size, v);
//        return bloomFilter;
//    }
//}
