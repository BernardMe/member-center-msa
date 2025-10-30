package com.zzjdyf.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 内置管理员登陆账号配置
 *
 * @author 阿隆
 * Created on 2021/7/20 3:14 下午.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "build-in.sys.manager")
public class BuiltInManagerConfig {
    /**
     * 管理员登陆账号列表
     */
    private List<Manager> list;

    /**
     * 管理员登陆账号
     */
    @Data
    public static class Manager {
        /**
         * 登陆用户名
         */
        private String username;
    }
}
