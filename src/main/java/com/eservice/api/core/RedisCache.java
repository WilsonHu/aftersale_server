//package com.eservice.api.core;
//
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
//import org.apache.ibatis.cache.Cache;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RedisCache {
//    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
//
//    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//
//    @Autowired
//    StringRedisTemplate redisTemplate;
//
//    private static final long EXPIRE_TIME_IN_SECONDS = 1800; // redis过期时间
//
//    /**
//     * Put query result to redis
//     *
//     * @param key
//     * @param value
//     */
//    @SuppressWarnings("unchecked")
//    public void put(String key, String value) {
//        RedisTemplate redisTemplate = getRedisTemplate();
//        ValueOperations opsForValue = redisTemplate.opsForValue();
//        opsForValue.set(key, value, EXPIRE_TIME_IN_SECONDS, TimeUnit.SECONDS);
//        logger.debug("Put query result to redis");
//    }
//
//    /**
//     *
//     * @param key
//     * @param value
//     * @param expireTime  The unit is seconds
//     */
//    @SuppressWarnings("unchecked")
//    public void putWithExpireTime(String key, String value, int expireTime) {
//        RedisTemplate redisTemplate = getRedisTemplate();
//        ValueOperations opsForValue = redisTemplate.opsForValue();
//        if(expireTime > 0) {
//            opsForValue.set(key, value, expireTime, TimeUnit.SECONDS);
//        } else {
//            opsForValue.set(key, value, EXPIRE_TIME_IN_SECONDS, TimeUnit.SECONDS);
//        }
//
//        logger.debug("Put query result to redis");
//    }
//
//    /**
//     * Get cached query result from redis
//     *
//     * @param key
//     * @return
//     */
//    public String get(String key) {
//        RedisTemplate redisTemplate = getRedisTemplate();
//        ValueOperations opsForValue = redisTemplate.opsForValue();
//        logger.debug("Get cached query result from redis");
//        return (String)opsForValue.get(key);
//    }
//
//    /**
//     * Remove cached query result from redis
//     *
//     * @param key
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public Object remove(Object key) {
//        RedisTemplate redisTemplate = getRedisTemplate();
//        redisTemplate.delete(key);
//        logger.debug("Remove cached query result from redis");
//        return null;
//    }
//
//    /**
//     * Clears this cache instance
//     */
//    public void clear() {
//        RedisTemplate redisTemplate = getRedisTemplate();
//        redisTemplate.execute((RedisCallback) connection -> {
//            connection.flushDb();
//            return null;
//        });
//        logger.debug("Clear all the cached query result from redis");
//    }
//
//    public ReadWriteLock getReadWriteLock() {
//        return readWriteLock;
//    }
//
//    private RedisTemplate getRedisTemplate() {
//        return redisTemplate;
//    }
//}