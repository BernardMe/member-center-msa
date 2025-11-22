package com.cheshun.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author 阿隆
 * Created on 2021/7/26 12:00 下午.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileConfig {
    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
    private Long maxSize;
    private FilePath mac;
    private FilePath linux;
    private FilePath windows;
    private String qrLogo;
    private int qrLogoRatio;
    private int qrWidth;
    private int qrHeight;
    private int qrMargin;

    public FilePath getPath() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return windows;
        } else if (os.toLowerCase().startsWith("mac")) {
            return mac;
        }
        return linux;
    }

    @Data
    public static class FilePath {
        private String path;
    }

    public Resource getQRLogoFile() {
        return resourceResolver.getResource(qrLogo);
    }
}
