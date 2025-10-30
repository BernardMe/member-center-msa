package com.zzjdyf.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "zzjdyf.baidu-ocr")
@Data
public class BaiduOCRProperties {

    private String apiKey;
    private String secretKey;

}
