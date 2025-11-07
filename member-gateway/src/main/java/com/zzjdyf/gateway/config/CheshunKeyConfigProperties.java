package com.zzjdyf.gateway.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@ToString
@RefreshScope
@ConfigurationProperties(prefix = "cheshun")
public class CheshunKeyConfigProperties {
    private String publicKey;

    private String privateKey;

    private Map<String,String> publicKeys;
}
