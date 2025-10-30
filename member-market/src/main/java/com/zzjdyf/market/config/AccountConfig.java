package com.zzjdyf.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangzhuo
 * Created on 20210802.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "account")
public class AccountConfig {
    private String name;
    private String bankName;
    private String accountNumber;
}
