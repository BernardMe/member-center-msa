package com.cheshun.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "zzjdyf.crm")
@Data
public class CrmProperties {

    private String entid;
    private String baseurl;

}
