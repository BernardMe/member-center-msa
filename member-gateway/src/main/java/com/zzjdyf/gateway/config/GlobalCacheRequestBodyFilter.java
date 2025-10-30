package com.zzjdyf.gateway.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.zzjdyf.gateway.common.GatewayContext;
import com.zzjdyf.gateway.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by xueqing wang on 2021/4/14 18:04
 *
 * @Description 将 request body 中的内容 copy 一份，记录到 exchange 的一个自定义属性中
 */
@Slf4j
@Configuration
public class GlobalCacheRequestBodyFilter implements GlobalFilter, Ordered {

    /**
     * default HttpMessageReader.
     */
    private static final List<HttpMessageReader<?>> MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("GlobalCacheRequestBodyFilter ...");
        ServerHttpRequest request = exchange.getRequest();
        final String path = request.getPath().pathWithinApplication().value();
        // GatewayContext中目前只有一个String的path和String的requestBody，如果需要别的参数在GatewayContext中追加即可
        final GatewayContext gatewayContext = new GatewayContext();
        gatewayContext.setPath(path);

        exchange.getAttributes().put(GatewayContext.CACHE_GATEWAY_CONTEXT, gatewayContext);

//        // 如果是GET，略过
        if ("GET".equals(exchange.getRequest().getMethodValue())) {
            return chain.filter(exchange);
        }
        // 如果没有缓存过，获取字节数组存入 exchange 的自定义属性中
        return DataBufferUtils.join(exchange.getRequest().getBody())

                .flatMap(dataBuffer -> {
                    DataBufferUtils.retain(dataBuffer);
                    final Flux<DataBuffer> cachedFlux = Flux.defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
                    final ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            return cachedFlux;
                        }
                    };
                    final ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
                    return cacheBody(mutatedExchange, chain, gatewayContext);

                });
    }


    @SuppressWarnings("unchecked")
    private Mono<Void> cacheBody(ServerWebExchange exchange, GatewayFilterChain chain, GatewayContext gatewayContext) {
        final HttpHeaders headers = exchange.getRequest().getHeaders();
        if (headers.getContentLength() == 0) {
            return chain.filter(exchange);
        }
        final ResolvableType resolvableType;
        if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(headers.getContentType())) {
            resolvableType = ResolvableType.forClassWithGenerics(MultiValueMap.class, String.class, Part.class);
        } else {
            resolvableType = ResolvableType.forClass(String.class);
        }
        return MESSAGE_READERS.stream().filter(reader -> reader.canRead(resolvableType, exchange.getRequest().getHeaders().getContentType())).findFirst()
                .orElseThrow(() -> new IllegalStateException("no suitable HttpMessageReader.")).readMono(resolvableType, exchange.getRequest(), Collections.emptyMap()).flatMap(resolvedBody -> {
                    if (resolvedBody instanceof MultiValueMap) {

                        MultiValueMap<String, Part> resolvedBody1 = (MultiValueMap<String, Part>) resolvedBody;
                        MapBuilder<String, Object> bodyBuilder = MapUtil.builder();
                        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
                        for (Map.Entry<String, List<Part>> stringListEntry : resolvedBody1.entrySet()) {
                            List<Part> value = stringListEntry.getValue();
                            Part part = CollUtil.getFirst(value);
                            if (part instanceof FormFieldPart) {
                                FormFieldPart part1 = (FormFieldPart) part;
                                bodyBuilder.put(stringListEntry.getKey(), part1.value());
                                multiValueMap.put(stringListEntry.getKey(), CollUtil.newArrayList(part1.value()));
                            } else if (part instanceof FilePart) {
                                FilePart part1 = (FilePart) part;
                                bodyBuilder.put(stringListEntry.getKey(), part);
                                multiValueMap.put(stringListEntry.getKey(), CollUtil.newArrayList(part));
                            }
                        }
                        gatewayContext.setFormData(multiValueMap);
                        gatewayContext.setCacheBody(JSONUtil.toJsonStr(bodyBuilder.build()));
                    } else {

                        String resolvedBody1 = null;
                        try {
                            resolvedBody1 = (String) resolvedBody;
                            Map<String, List> queryParams = URLUtil.getQueryParamsNo(resolvedBody1);
                            if (MapUtil.isNotEmpty(queryParams)) {
                                MapBuilder<String, Object> bodyBuilder = MapUtil.builder();
                                MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
                                for (Map.Entry<String, List> stringListEntry : queryParams.entrySet()) {
                                    bodyBuilder.put(stringListEntry.getKey(), stringListEntry.getValue());
                                    multiValueMap.put(stringListEntry.getKey(), stringListEntry.getValue());
                                }
                                gatewayContext.setFormData(multiValueMap);
                                gatewayContext.setCacheBody(JSONUtil.toJsonStr(bodyBuilder.build()));
                            } else {
                                gatewayContext.setCacheBody(resolvedBody1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            gatewayContext.setCacheBody(resolvedBody1);
                        }
                    }
                    return chain.filter(exchange);
                });
    }


    @Override
    public int getOrder() {
        return -30;
    }
}


