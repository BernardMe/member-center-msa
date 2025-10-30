package com.zzjdyf.market.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
public class Swagger2Config {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("DSBETC营销API文档")
                        .description("DSBETC营销API文档")
                        .termsOfServiceUrl("")
                        .contact(new Contact("阿隆", "", ""))
                        .version("1.0")
                        .build())
                // 分组名称
                .groupName("1.0版本")
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.cheshun"))
                .paths(PathSelectors.any())
                .build();
    }
}
