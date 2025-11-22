package com.cheshun.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 阿隆
 * Created on 2021/6/18 11:49 上午.
 */
@Configuration
@ConfigurationProperties(prefix = "http.proxy")
@Data
public class ProxyConfig {
    /**
     * 是否启用代理
     */
    private Boolean enabled = false;
    /**
     * 代理主机地址
     */
    private String host;
    /**
     * 代理端口
     */
    private Integer port;
}
