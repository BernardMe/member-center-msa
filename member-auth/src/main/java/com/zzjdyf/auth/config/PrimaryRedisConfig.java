package com.zzjdyf.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzjdyf.common.constant.JsonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 主数据库
 */
@Slf4j
@Configuration
public class PrimaryRedisConfig {

    @Primary
    @Bean(name = "defaultRedisConnectionFactory")
    public RedisConnectionFactory defaultRedisConnectionFactory(
            @Value("${spring.redis.host}") String host,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.password}") String password,
            @Value("${spring.redis.database}") int database) {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setDatabase(database); // 使用默认db4
        // 仅当 password 不为空时设置密码
        if (!"123456".equals(password)) {
            config.setPassword(RedisPassword.of(password));
        }
        return new LettuceConnectionFactory(config);
    }

    @Primary
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> singleTemplate(@Qualifier(JsonConstant.OBJECT_MAPPER) ObjectMapper objectMapper,
                                                        @Qualifier("defaultRedisConnectionFactory") RedisConnectionFactory defaultRedisConnectionFactory){
        return redisTemplate(objectMapper, defaultRedisConnectionFactory);
    }

    private RedisTemplate<String, Object> redisTemplate(ObjectMapper objectMapper
            , RedisConnectionFactory defaultRedisConnectionFactory) {
        log.info("开始创建redis模板对象...");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultRedisConnectionFactory);
        // Key 保持字符串序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 设置对象序列化映射
        serializer.setObjectMapper(objectMapper);
        // 必须使用 JDK 序列化器读取旧数据
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        return redisTemplate;
    }
}