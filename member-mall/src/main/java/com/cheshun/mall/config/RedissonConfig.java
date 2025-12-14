package com.cheshun.mall.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.database:0}")
    private int database;

    @Value("${spring.redis.password:}")
    private String password;

    //@Value("${spring.redis.sentinel.master:}")
    private String master;

    //@Value("${spring.redis.sentinel.nodes:}")
    private String[] nodes;

    @Value("${spring.redis.host:}")
    private String host;

    @Value("${spring.redis.port:}")
    private Integer port;

    @Bean
    public RedissonClient redissonClient() {
        //if (nodes.length == 0 || !StringUtils.hasText(master)) {
            Config config = new Config();
            config.useSingleServer().setAddress("redis://" + host + ":" + port);
            return Redisson.create(config);
        //}
        /*Config config = new Config();
        config.useSentinelServers().setMasterName(master).setDatabase(database)
                .setPassword(!StringUtils.hasText(password) ? null : password)
                .setCheckSentinelsList(false)
                .addSentinelAddress(Arrays.stream(nodes).map(t -> "redis://" + t).toArray(String[]::new));
        return Redisson.create(config);*/
    }
}
