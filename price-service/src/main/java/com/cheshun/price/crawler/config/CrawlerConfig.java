package com.cheshun.price.crawler.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 爬虫配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "crawler")
public class CrawlerConfig {

    /**
     * 是否启用爬虫
     */
    private Boolean enabled = true;

    /**
     * 请求超时时间（毫秒）
     */
    private Integer timeout = 10000;

    /**
     * 重试次数
     */
    private Integer retryCount = 3;

    /**
     * 请求间隔（毫秒）防止被封
     */
    private Integer requestInterval = 2000;

    /**
     * User-Agent 列表（随机选择，模拟真实浏览器）
     */
    private String[] userAgents = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:121.0) Gecko/20100101 Firefox/121.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.1 Safari/605.1.15"
    };

    /**
     * 代理配置（可选）
     */
    private ProxyConfig proxy = new ProxyConfig();

    /**
     * 平台配置
     */
    private Map<String, PlatformConfig> platforms = new HashMap<>();

    @Data
    public static class ProxyConfig {
        private Boolean enabled = false;
        private String host;
        private Integer port;
        private String username;
        private String password;
    }

    @Data
    public static class PlatformConfig {
        /**
         * 平台名称
         */
        private String name;

        /**
         * 搜索URL模板
         */
        private String searchUrl;

        /**
         * 是否启用
         */
        private Boolean enabled = true;

        /**
         * 需要登录Cookie（某些平台需要）
         */
        private String cookie;
    }
}