package com.zlf.commonredis.utils;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@Slf4j
public class RedissonUtils {


    public static RLock Lock(String key) {
        RedissonClient redissonClient = SpringUtil.getBean(RedissonClient.class);
        RLock rLock = redissonClient.getLock(key);
        rLock.lock();
        return rLock;
    }

    public static RLock getLock(String key) {
        RedissonClient redissonClient = SpringUtil.getBean(RedissonClient.class);
        RLock rLock = redissonClient.getLock(key);
        return rLock;
    }


    public static void unLock(RLock rLock) {
        rLock.unlock();
    }

}