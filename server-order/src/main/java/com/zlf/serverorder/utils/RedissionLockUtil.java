package com.zlf.serverorder.utils;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * @author ：zhenglifei
 * @description ：RedissionLockUtil.class
 * @date ：Created in 2021/4/8 13:19
 */
@Slf4j
public class RedissionLockUtil {

    /**
     *
     * @param key
     */
    public static RLock lock(String key){
        RedissonClient redissonClient = SpringUtil.getBean(RedissonClient.class);
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        return lock;
    }

    /**
     *
     * @param key
     */
    public static RLock getLock(String key){
        RedissonClient redissonClient = SpringUtil.getBean(RedissonClient.class);
        RLock lock = redissonClient.getLock(key);
        return lock;
    }


    /**
     *
     * @param lock
     */
    public static void unlock(RLock lock){
        lock.unlock();
    }
}
