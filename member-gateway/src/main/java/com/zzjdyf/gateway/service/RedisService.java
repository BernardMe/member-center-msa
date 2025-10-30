package com.zzjdyf.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by xueqing wang on 2021/7/5 9:27
 */
@Component
@Slf4j
public class RedisService {
    /**
     * Jedis sentinel对象池 所有Jedis对象均通过该池租赁获取
     */
    private static JedisSentinelPool sentinelPool;

    /**
     * Jedis single对象池 所有Jedis对象均通过该池租赁获取
     */
    private static JedisPool jedisPool;

    /**
     * 缓存数据成功
     */
    private final static String CACHE_INFO_SUCCESS = "OK";


    /**
     * env
     */
    private static String env;

    /**
     * @author xueqing wang
     * @date 2021/7/5
     * 注入JedisSentinelPool
     */
    @Autowired(required = false)
    private void setSentinelPool(JedisSentinelPool sentinelPool) {
        RedisService.sentinelPool = sentinelPool;
    }


    @Value("${spring.profiles.active:dev}")
    private void setEnv(String env) {
        RedisService.env = env;
    }

    /**
     * @author xueqing wang
     * @date 2021/7/5
     * 注入JedisPool
     */
    @Autowired(required = false)
    private void setJedisPool(JedisPool jedisPool) {
        RedisService.jedisPool = jedisPool;
    }


    /**
     * @return redis.clients.jedis.Jedis
     * 获取到Jedis
     * @author xueqing wang
     * @date 2021/7/5 下午7:35
     */
    private static Jedis getJedis() {
        Jedis jedis;
        try {
            log.info("env:{}", env);
            if ("dev".equals(env)) {
                jedis = jedisPool.getResource();
            } else {
                jedis = sentinelPool.getResource();
            }
            return jedis;
        } catch (JedisConnectionException e) {
            log.error("获取Redis 异常", e);
            throw e;
        }
    }

    /**
     * @author xueqing wang
     * @date 2021/7/5 下午7:36
     * 缓存String类型的数据,数据不过期
     */
    public boolean setString(String key, String value) throws Exception {
        return setString(key, value, -1);
    }


    /**
     * @author xueqing wang
     * @date 2021/7/5 下午7:40
     * 缓存String类型的数据并设置超时时间
     */
    public boolean setString(String key, String value, Integer timeout) {
        String result;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            result = jedis.set(key, value);
            if (timeout != -1) {
                jedis.expire(key, timeout);
            }
            if (CACHE_INFO_SUCCESS.equals(result)) {
                return true;
            } else {
                return false;
            }
        } finally {
            releaseRedis(jedis);
        }
    }

    /**
     * @author xueqing wang
     * @date 2021/7/5 下午7:46
     * 获取String类型的数据
     */
    public String getString(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            throw e;
        } finally {
            releaseRedis(jedis);
        }
    }


    /**
     * @return void
     * 释放Jedis
     * @author xueqing wang
     * @date 2021/7/5 下午7:49
     */
    public static void releaseRedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    /**
     * @return boolean
     * 判断redis中key是否存在
     * @author xueqing wang
     * @date 2021/7/5 下午7:55
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            throw e;
        } finally {
            releaseRedis(jedis);
        }
    }


    /**
     * @return boolean
     * 通过key删除缓存中数据
     * @author xueqing wang
     * @date 2021/7/5 下午7:55
     */
    public boolean del(String key)  {
        Long num;
        Jedis jedis = null;
        Boolean result = false;
        try {
            jedis = getJedis();
            num = jedis.del(key);
            if (num.equals(1L)) {
                result = true;
            }
        } finally {
            releaseRedis(jedis);
        }
        return result;
    }

    /**
     * @return byte[]
     * 通过key和field获取缓存中数据
     * @author xueqing wang
     * @date 2021/7/5 下午7:55
     */
    public byte[] hget(byte[] key, byte[] field) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hget(key, field);
        } catch (Exception e) {
            throw e;
        } finally {
            releaseRedis(jedis);
        }
    }
}
