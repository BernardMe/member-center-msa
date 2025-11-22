package com.cheshun.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/7/28 9:57 上午.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "goods")
public class GoodsBrandConfig {

    private List<Brand> brandList;

    @Data
    public static class Brand {
        private String name;
        private boolean enable;
        private List<Model> modelList;
    }

    @Data
    public static class Model {
        private String name;
        private boolean enable;
    }
}
