package com.zzjdyf.gateway.config;

import cn.hutool.core.util.IdUtil;
import com.cheshun.common.Const;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @calssName LogFilter
 * @Description 生成全局日志唯一ID
 * @Author xueqing wang
 * @DATE 2021/10/27 14:15
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class LogFilter implements GlobalFilter, Ordered {


    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 构建新的请求头
        HttpHeaders headers = new HttpHeaders();
        ServerHttpRequest oldRequest = exchange.getRequest();
        headers.putAll(oldRequest.getHeaders());
        String uuid = IdUtil.simpleUUID();
        MDC.put(Const.REQUEST_ID, uuid);
        headers.add(Const.REQUEST_ID, uuid);
        URI uri = oldRequest.getURI();
        ServerHttpRequest newRequest = oldRequest.mutate().uri(uri).build();
        newRequest = new ServerHttpRequestDecorator(newRequest) {
            @Override
            public HttpHeaders getHeaders() {
                return headers;
            }
        };
        return chain.filter(exchange.mutate().request(newRequest).build());
    }


}

