package com.zzjdyf.gateway.config;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by xueqing wang on 2021/6/24 21:07
 */
@Configuration
@Slf4j
public class RedisConfig {


    @Value("${spring.redis.sentinel.master:mymaster}")
    private String master;
    @Value("${spring.redis.sentinel.nodes:127.0.0.1:6379}")
    private String nodes;
    @Value("${spring.redis.password:}")
    String password;


    @Value("${spring.redis.host:127.0.0.1}")
    String host;
    @Value("${spring.redis.port:6379}")
    Integer port;


    /**
     * 最大连接数
     */
    @Value("${spring.redis.max-active:100}")
    private int maxTotal;

    /**
     * 最大空闲数
     */
    @Value("${spring.redis.max-idle:20}")
    private int maxIdle;

    /**
     * 最小空闲数
     */
    @Value("${spring.redis.min-idle:8}")
    private int minIdle;

    /**
     * 连接超时时间
     */
    @Value("${spring.redis.min-wait:30000}")
    private int timeout;


    @Bean(value = "jedisPoolConfig")
    public JedisPoolConfig initJedisPoolConfig() {
        log.info("JedisPool initialize start ...");
        JedisPoolConfig config = new JedisPoolConfig();

        //最大总量
        config.setMaxTotal(maxTotal);
        //设置最大空闲数量
        config.setMaxIdle(maxIdle);
        //设置最小空闲数量
        config.setMinIdle(minIdle);
        //常规配置
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        log.info("JedisPool initialize end ...");
        return config;
    }


    /**
     * @return redis.clients.jedis.JedisSentinelPool
     * 生成JedisSentinelPool并且放入Spring容器
     * @author xueqing wang
     * @date 下午7:20
     */
    @Bean(value = "sentinelPool")
    @Profile({"pro","test"})
    public JedisSentinelPool initSentinelPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {

        Set<String> nodeSet = new HashSet<>();
        //获取到节点信息
        String nodeString = nodes;
        //判断字符串是否为空
        if (nodeString == null || "".equals(nodeString)) {
            log.error("RedisSentinelConfiguration initialize error nodeString is null");
            throw new RuntimeException("RedisSentinelConfiguration initialize error nodeString is null");
        }
        String[] nodeArray = nodeString.split(",");
        //判断是否为空
        if (nodeArray == null || nodeArray.length == 0) {
            log.error("RedisSentinelConfiguration initialize error nodeArray is null");
            throw new RuntimeException("RedisSentinelConfiguration initialize error nodeArray is null");
        }
        //循环注入至Set中
        for (String node : nodeArray) {
            log.info("Read node : {}。", node);
            nodeSet.add(node);
        }
        //创建连接池对象
        JedisSentinelPool jedisPool = new JedisSentinelPool(master, nodeSet, jedisPoolConfig, timeout, password);

        return jedisPool;
    }


    /**
     * @return redis.clients.jedis.JedisSentinelPool
     * 生成JedisSentinelPool并且放入Spring容器
     * @author xueqing wang
     * @date 下午7:20
     */
    @Bean(value = "redisPool")
    @Profile("dev")
    public JedisPool initRedisPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
        if (StrUtil.isNotBlank(password)) {
            return new JedisPool(jedisPoolConfig, host, port, timeout, password);
        }
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }
}
