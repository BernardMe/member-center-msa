package com.zzjdyf.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Configuration
@ConfigurationProperties(prefix = "login")
@Data
public class LoginConfig {
    /**
     * 登陆token配置
     */
    private Token token;

    @Data
    public static class Token {
        /**
         * token缓存前缀
         */
        private String cachePrefix;
        /**
         * token缓存失效时长
         */
        private Duration cacheTtl;
        /**
         * token加密密钥
         */
        private String secretKey;
    }
}
