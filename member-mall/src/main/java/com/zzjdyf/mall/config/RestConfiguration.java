package com.zzjdyf.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

/**
 * Created by xueqing wang on 2021/6/18 11:50
 */
@Configuration
public class RestConfiguration {
    @Bean(name="restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean(name="restProxyTemplate")
    public RestTemplate restTemplateWithProxy(ProxyConfig proxyConfig) {
        if (proxyConfig.getEnabled()) {
            SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
            httpRequestFactory.setReadTimeout(60000);
            httpRequestFactory.setConnectTimeout(60000);
            SocketAddress address = new InetSocketAddress(proxyConfig.getHost(), proxyConfig.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            httpRequestFactory.setProxy(proxy);
            return new RestTemplate(httpRequestFactory);
        }
        return new RestTemplate();
    }
}
