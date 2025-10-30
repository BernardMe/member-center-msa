package com.zzjdyf.gateway.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.*;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.gateway.common.GatewayContext;
import com.zzjdyf.gateway.constant.ConstantFilter;
import com.zzjdyf.gateway.serializer.KryoSerializer;
import com.zzjdyf.gateway.service.RedisService;
import com.zzjdyf.gateway.util.AESContextHolder;
import com.zzjdyf.gateway.util.Permutation;
import io.netty.buffer.ByteBufAllocator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @calssName SignFilter
 * @Description 请求参数解密, 验签
 * @Author xueqing wang
 * @DATE 2021/4/17 14:15
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ReqDecryptFilter implements GlobalFilter, Ordered {

    /**
     * token前缀
     */
    private String tokenPrefix = "csii:app:";

    private String tokenHeader = "token";

    @Value("${cheshun.submitToken.frequencyTime:60000}")
    private Integer frequencyTime;

    private final cheshunKeyConfigProperties cheshunKeyConfigProperties;

    private final cheshunNoEncryptionPathsProperties cheshunNoEncryptionPathsProperties;


    private final RedisService redisService;

    private final KryoSerializer kryoSerializer;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String userId = "";
        // 获取请求的方法
        ServerHttpRequest oldRequest = exchange.getRequest();
        String method = oldRequest.getMethodValue();
        MediaType contentType = oldRequest.getHeaders().getContentType();
        String token = oldRequest.getHeaders().getFirst(tokenHeader);
        if (StringUtils.hasText(token)) {
            log.info("token:{}", token);
            //兼容科蓝redis封装  获取userId
            byte[] serialize = kryoSerializer.serialize(tokenPrefix.concat(token));
            byte[] userIds = kryoSerializer.serialize("userId");
            byte[] hget = redisService.hget(serialize, userIds);
//            log.info("key:{}", StrUtil.str(serialize, CharsetUtil.UTF_8));
//            log.info("filed:{}", StrUtil.str(userIds, CharsetUtil.UTF_8));
//            log.info("value:{}", StrUtil.str(hget, CharsetUtil.UTF_8));
            userId = (kryoSerializer.deSerialize(hget) + "");
            log.info("userId:{}", userId);
        }

        log.info("前Header:{}", oldRequest.getHeaders());
        URI uri = oldRequest.getURI();

        Optional.ofNullable(token)
                .map(
                        s -> Optional.ofNullable(redisService.exists(s + "_" + uri.getPath())).orElse(false)
                )
                .ifPresent(s -> {
                    if (s) {
                        log.info("获取该交易:{}", uri.getPath());
                        throw new RRException("请勿重复提交！", uri.getPath());
                    }
                    //存储该交易
                    log.info("存储该交易：{},值为：{}", token + "_" + uri.getPath(), 1);
                    redisService.setString(token + "_" + uri.getPath(), "1", frequencyTime);
                });


        log.info("请求路径：{}", uri.getPath());

        if (cheshunNoEncryptionPathsProperties.getPaths().stream().anyMatch(s -> uri.getPath().toLowerCase().contains(s))) {
            log.info("请求路径：{}，不进行加解密操作", uri.getPath());
            // 构建新的请求头
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            if (StringUtils.hasText(userId)) {
                headers.set("UserId", userId);
            }
            log.info("后Header:{}", headers);
            ServerHttpRequest newRequest = oldRequest.mutate().uri(uri).build();
            newRequest = new ServerHttpRequestDecorator(newRequest) {
                @Override
                public HttpHeaders getHeaders() {
                    return headers;
                }
            };

            // 把解密后的数据重置到exchange自定义属性中,在之后的日志GlobalLogFilter从此处获取请求参数打印日志
//        gatewayContext.setCacheBody(StrUtil.utf8Str(decrypBytes));
//        exchange.getAttributes().put(GatewayContext.CACHE_GATEWAY_CONTEXT, gatewayContext);

            return chain.filter(exchange.mutate().request(newRequest).build());
        }

        boolean postOrPutApplicationJson = (HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method)) && (MediaType.APPLICATION_JSON.equals(contentType) || MediaType.APPLICATION_JSON_UTF8.equals(contentType));
        boolean postOrPutFormData = (HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method)) && (MediaType.MULTIPART_FORM_DATA.equals(contentType) || StrUtil.startWith(contentType.toString(), MediaType.MULTIPART_FORM_DATA.toString()));
        boolean postOrPutFormUrlEncode = (HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method)) && (MediaType.APPLICATION_FORM_URLENCODED.equals(contentType) || StrUtil.startWith(contentType.toString(), MediaType.APPLICATION_FORM_URLENCODED.toString()));
        GatewayContext gateWayContext = exchange.getAttributeOrDefault(GatewayContext.CACHE_GATEWAY_CONTEXT, null);
        if (postOrPutApplicationJson) {
            return getPostJsonVoidMono(exchange, chain, oldRequest, uri, gateWayContext, userId);
        } else if (postOrPutFormUrlEncode) {
            return getPostFormUrlEncodeVoidMono(exchange, chain, oldRequest, uri, gateWayContext, userId);
        } else if (postOrPutFormData) {
            //暂不支持
//            return getPostFormDataVoidMono(exchange, chain, oldRequest, uri, gateWayContext);
            return chain.filter(exchange);
        } else if ("GET".equals(method)) { //
            return getGetVoidMono(exchange, chain, oldRequest, uri, gateWayContext, userId);
        }
        return chain.filter(exchange);
    }


    private Mono<Void> getPostFormUrlEncodeVoidMono(ServerWebExchange exchange, GatewayFilterChain chain, ServerHttpRequest request, URI uri, GatewayContext gatewayContext, String userId) {
        HttpHeaders headers = request.getHeaders();
        String timestamp = exchange.getRequest().getHeaders().getFirst(ConstantFilter.TIMESTAMP);
        String algorithm = exchange.getRequest().getHeaders().getFirst(ConstantFilter.ALGORITHM);
        String sequenceId = exchange.getRequest().getHeaders().getFirst(ConstantFilter.SEQUENCE_ID);
        String clientType = exchange.getRequest().getHeaders().getFirst(ConstantFilter.CLIENT_TYPE);
        return Mono.defer(() -> {
            Charset charset = headers.getContentType().getCharset();
            charset = charset == null ? StandardCharsets.UTF_8 : charset;
            String charsetName = charset.name();
            MultiValueMap<String, Object> formData =
                    gatewayContext.getFormData();
            /** formData is empty just return */
            if (null == formData || formData.isEmpty()) {
                return chain.filter(exchange);
            }
            StringBuilder formDataBodyBuilder = new StringBuilder();
            String entryKey;
            List<Object> entryValue;
            try {
                String messageDigest = URLUtil.decode(formData.getFirst(ConstantFilter.MESSAGE_DIGEST) + "");
                String signature = URLUtil.decode(formData.getFirst(ConstantFilter.SIGNATURE) + "");
                String payload = URLUtil.decode(formData.getFirst(ConstantFilter.PAYLOAD) + "");

                //通过sequenceId 获取调用方公钥
                String publicClientKey = cheshunKeyConfigProperties.getPublicKeys().get(sequenceId);
                //验签
                Sign sign = new Sign(algorithm, null, publicClientKey);


                //ASCII 排序
                Map<Object, Object> sort =
                        MapUtil.builder()
                                .put(ConstantFilter.SEQUENCE_ID, sequenceId)
                                .put(ConstantFilter.TIMESTAMP, timestamp)
                                .put(ConstantFilter.ALGORITHM, algorithm)
                                .put(ConstantFilter.CLIENT_TYPE, clientType)
                                .put(ConstantFilter.MESSAGE_DIGEST, messageDigest)
                                .put(ConstantFilter.PAYLOAD, payload)
                                .build();

                log.info("签名数据为：{}", sort);

                boolean verify = sign.verify(StrUtil.bytes(Permutation.sort(sort)), Base64.decode(signature));
                if (!verify) {
                    throw new RRException("验签失败");
                }

                log.info("验签成功：{}", Permutation.sort(sort));
                log.info("加密的payload：{}", payload);
                //解密
                RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), cheshunKeyConfigProperties.getPrivateKey(), null);

                messageDigest = rsa.decryptStr(messageDigest, KeyType.PrivateKey);
                log.info("解密后的AES秘钥：{}", messageDigest);
                try {
                    payload = new AES(Mode.CBC, Padding.PKCS5Padding, StrUtil.bytes(messageDigest), "0000000000000000".getBytes()).decryptStr(payload);
                    AESContextHolder.setAESModeAndPadding(Mode.CBC);
                } catch (Exception e) {
                    payload = new AES(Mode.ECB, Padding.PKCS5Padding, StrUtil.bytes(messageDigest)).decryptStr(payload);
                    AESContextHolder.setAESModeAndPadding(Mode.ECB);
                }
                log.info("解密后的客户端数据：{}", payload);

                Map<String, Object> map = JSONUtil.toBean(payload, Map.class);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();

                    Object value = entry.getValue();

                    if (value instanceof List) {
                        List list = (List) value;
                        for (Object o : list) {
                            formDataBodyBuilder.append(key).append("=")
                                    .append(
                                            URLUtil.encode(o.toString(), charsetName))
                                    .append("&");
                        }
                    } else {
                        formDataBodyBuilder
                                .append(key).append("=").append(URLUtil
                                .encode(value.toString(), charsetName))
                                .append("&");
                    }

                }


                /* repackage form data */
                for (Map.Entry<String, List<Object>> entry : formData
                        .entrySet()) {
                    entryKey = entry.getKey();
                    if (
                            StrUtil.equals(entryKey, ConstantFilter.MESSAGE_DIGEST)
                                    ||
                                    StrUtil.equals(entryKey, ConstantFilter.PAYLOAD)
                                    || StrUtil.equals(entryKey, ConstantFilter.SIGNATURE)

                    ) {
                        continue;
                    }
                    entryValue = entry.getValue();
                    if (entryValue.size() > 1) {
                        for (Object value : entryValue) {
                            formDataBodyBuilder.append(entryKey).append("=")
                                    .append(
                                            URLUtil.encode(value + "", charsetName))
                                    .append("&");
                        }
                    } else {
                        formDataBodyBuilder
                                .append(entryKey).append("=").append(URLUtil
                                .encode(entryValue.get(0) + "", charsetName))
                                .append("&");
                    }
                }
            } catch (Exception e) {
                // ignore URLEncode Exception
                e.printStackTrace();
                throw new RRException(e.getMessage());
            }
            /* substring with the last char '&' */
            String formDataBodyString = "";
            if (formDataBodyBuilder.length() > 0) {
                formDataBodyString = formDataBodyBuilder.substring(0,
                        formDataBodyBuilder.length() - 1);
            }

            // date data bytes
            byte[] bodyBytes = formDataBodyString.getBytes(charset);
            int contentLength = bodyBytes.length;
            ServerHttpRequestDecorator decorator =
                    new ServerHttpRequestDecorator(
                            request) {

                        @Override
                        public HttpHeaders getHeaders() {
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.putAll(super.getHeaders());
                            if (StringUtils.hasText(userId)) {
                                headers.set("UserId", userId);
                            }
                            if (contentLength > 0) {
                                httpHeaders.setContentLength(contentLength);
                            } else {
                                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING,
                                        "chunked");
                            }
                            return httpHeaders;
                        }

                        @Override
                        public Flux<DataBuffer> getBody() {
                            Flux<DataBuffer> read = DataBufferUtils
                                    .read(new ByteArrayResource(bodyBytes),
                                            new NettyDataBufferFactory(
                                                    ByteBufAllocator.DEFAULT),
                                            contentLength);
                            read.subscribe(DataBufferUtils::release);
                            return read;
                        }
                    };
            ServerWebExchange mutateExchange =
                    exchange.mutate().request(decorator).build();
                /*   log.info("[GatewayContext]Rewrite Form Data :{}",
                       formDataBodyString);*/

            return chain.filter(mutateExchange);
        });

    }


    private Mono<Void> getPostFormDataVoidMono(ServerWebExchange exchange, GatewayFilterChain chain, ServerHttpRequest request, URI uri, GatewayContext gatewayContext, String userId) {
        HttpHeaders headers = request.getHeaders();
        String timestamp = exchange.getRequest().getHeaders().getFirst(ConstantFilter.TIMESTAMP);
        String algorithm = exchange.getRequest().getHeaders().getFirst(ConstantFilter.ALGORITHM);
        String sequenceId = exchange.getRequest().getHeaders().getFirst(ConstantFilter.SEQUENCE_ID);
        String clientType = exchange.getRequest().getHeaders().getFirst(ConstantFilter.CLIENT_TYPE);
        return Mono.defer(() -> {
            Charset charset = headers.getContentType().getCharset();
            charset = charset == null ? StandardCharsets.UTF_8 : charset;
            String charsetName = charset.name();
            MultiValueMap<String, Object> formData =
                    gatewayContext.getFormData();
            /** formData is empty just return */
            if (null == formData || formData.isEmpty()) {
                return chain.filter(exchange);
            }
            StringBuilder formDataBodyBuilder = new StringBuilder();


            String entryKey;
            List<Object> entryValue;
            try {
                String messageDigest = formData.getFirst(ConstantFilter.MESSAGE_DIGEST) + "";
                String signature = formData.getFirst(ConstantFilter.SIGNATURE) + "";
                String payload = formData.getFirst(ConstantFilter.PAYLOAD) + "";

                //通过sequenceId 获取调用方公钥
                String publicClientKey = cheshunKeyConfigProperties.getPublicKeys().get(sequenceId);
                //验签
                Sign sign = new Sign(algorithm, null, publicClientKey);


                //ASCII 排序
                Map<Object, Object> sort =
                        MapUtil.builder()
                                .put(ConstantFilter.SEQUENCE_ID, sequenceId)
                                .put(ConstantFilter.TIMESTAMP, timestamp)
                                .put(ConstantFilter.ALGORITHM, algorithm)
                                .put(ConstantFilter.CLIENT_TYPE, clientType)
                                .put(ConstantFilter.MESSAGE_DIGEST, messageDigest)
                                .put(ConstantFilter.PAYLOAD, payload)
                                .build();

                log.info("签名数据为：{}", sort);

                boolean verify = sign.verify(StrUtil.bytes(Permutation.sort(sort)), Base64.decode(signature));
                if (!verify) {
                    throw new RRException("验签失败");
                }

                log.info("验签成功：{}", Permutation.sort(sort));
                log.info("加密的payload：{}", payload);
                //解密
                RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), cheshunKeyConfigProperties.getPrivateKey(), null);

                messageDigest = rsa.decryptStr(messageDigest, KeyType.PrivateKey);
                log.info("解密后的AES秘钥：{}", messageDigest);

                try {
                    payload = new AES(Mode.CBC, Padding.PKCS5Padding, StrUtil.bytes(messageDigest), "0000000000000000".getBytes()).decryptStr(payload);
                    AESContextHolder.setAESModeAndPadding(Mode.CBC);
                } catch (Exception e) {
                    payload = new AES(Mode.ECB, Padding.PKCS5Padding, StrUtil.bytes(messageDigest)).decryptStr(payload);
                    AESContextHolder.setAESModeAndPadding(Mode.ECB);
                }

                log.info("解密后的客户端数据：{}", payload);

                Map<String, Object> map = JSONUtil.toBean(payload, Map.class);

                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();

                    Object value = entry.getValue();

                    if (value instanceof List) {
                        List list = (List) value;
                        for (Object o : list) {
                            formDataBodyBuilder.append("--WebAppBoundary")
                                    .append("\r")
                                    .append("Content-Disposition: form-data; name='" + key + "'")
                                    .append("\r")
                                    .append("Content-Type: text/plain")
                                    .append("\r\n")
                                    .append(o);
                        }
                    } else {
                        formDataBodyBuilder.append("--WebAppBoundary")
                                .append("\r")
                                .append("Content-Disposition: form-data; name='" + key + "'")
                                .append("\r")
                                .append("Content-Type: text/plain")
                                .append("\r\n")
                                .append(
                                        URLUtil.encode(value + "", charsetName));
                    }

                }
            } catch (Exception e) {
                // ignore URLEncode Exception
                e.printStackTrace();
                throw new RRException(e.getMessage());
            }
            /* substring with the last char '&' */
            String formDataBodyString = "";
            if (formDataBodyBuilder.length() > 0) {
                formDataBodyString = formDataBodyBuilder.substring(0,
                        formDataBodyBuilder.length() - 1);
            }


            // date data bytes
            byte[] bodyBytes = formDataBodyString.getBytes(StandardCharsets.UTF_8);
            int contentLength = bodyBytes.length;
            ServerHttpRequestDecorator decorator =
                    new ServerHttpRequestDecorator(
                            request) {


                        @Override
                        public HttpHeaders getHeaders() {
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.putAll(super.getHeaders());
                            if (StringUtils.hasText(userId)) {
                                headers.set("UserId", userId);
                            }
                            if (contentLength > 0) {
                                httpHeaders.setContentLength(contentLength);
                            } else {
                                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING,
                                        "chunked");
                            }
                            return httpHeaders;
                        }

                        @Override
                        public Flux<DataBuffer> getBody() {
                            Flux<DataBuffer> read = DataBufferUtils
                                    .read(new ByteArrayResource(bodyBytes),
                                            new NettyDataBufferFactory(
                                                    ByteBufAllocator.DEFAULT),
                                            contentLength);

                            read.subscribe(DataBufferUtils::release);
                            return read;
                        }
                    };
            ServerWebExchange mutateExchange =
                    exchange.mutate().request(decorator).build();
                /*   log.info("[GatewayContext]Rewrite Form Data :{}",
                       formDataBodyString);*/


            return chain.filter(mutateExchange);
        });
    }


    /**
     * 处理get请求验签解密
     *
     * @param exchange
     * @param chain
     * @param oldRequest
     * @param uri
     * @param userId
     * @return
     */
    private Mono<Void> getGetVoidMono(ServerWebExchange exchange, GatewayFilterChain chain, ServerHttpRequest oldRequest, URI uri, GatewayContext gatewayContext, String userId) {
        String timestamp = exchange.getRequest().getHeaders().getFirst(ConstantFilter.TIMESTAMP);
        String algorithm = exchange.getRequest().getHeaders().getFirst(ConstantFilter.ALGORITHM);
        String sequenceId = exchange.getRequest().getHeaders().getFirst(ConstantFilter.SEQUENCE_ID);
        String clientType = exchange.getRequest().getHeaders().getFirst(ConstantFilter.CLIENT_TYPE);

        String payload = (exchange.getRequest().getQueryParams().getFirst(ConstantFilter.PAYLOAD));
        String signature = (exchange.getRequest().getQueryParams().getFirst(ConstantFilter.SIGNATURE));
        String messageDigest = (exchange.getRequest().getQueryParams().getFirst(ConstantFilter.MESSAGE_DIGEST));
        if (StrUtil.isBlank(signature)) {
            throw new RRException("验签失败");
        }

        //通过sequenceId 获取调用方公钥
        String publicClientKey = cheshunKeyConfigProperties.getPublicKeys().get(sequenceId);
        //验签
        Sign sign = new Sign(algorithm, null, publicClientKey);


        //ASCII 排序
        Map<Object, Object> sort =
                MapUtil.builder()
                        .put(ConstantFilter.SEQUENCE_ID, sequenceId)
                        .put(ConstantFilter.TIMESTAMP, timestamp)
                        .put(ConstantFilter.ALGORITHM, algorithm)
                        .put(ConstantFilter.CLIENT_TYPE, clientType)
                        .put(ConstantFilter.MESSAGE_DIGEST, messageDigest)
                        .put(ConstantFilter.PAYLOAD, payload)
                        .build();


        log.info("签名数据为：{}", sort);

        boolean verify = sign.verify(StrUtil.bytes(Permutation.sort(sort)), Base64.decode(signature));
        if (!verify) {
            throw new RRException("验签失败");
        }

        log.info("验签成功：{}", Permutation.sort(sort));
        log.info("加密的payload：{}", payload);
        //解密
        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), cheshunKeyConfigProperties.getPrivateKey(), null);

        messageDigest = rsa.decryptStr(messageDigest, KeyType.PrivateKey);
        log.info("解密后的AES秘钥：{}", messageDigest);
        try {
            payload = new AES(Mode.CBC, Padding.PKCS5Padding, StrUtil.bytes(messageDigest), "0000000000000000".getBytes()).decryptStr(payload);
            AESContextHolder.setAESModeAndPadding(Mode.CBC);
        } catch (Exception e) {
            payload = new AES(Mode.ECB, Padding.PKCS5Padding, StrUtil.bytes(messageDigest)).decryptStr(payload);
            AESContextHolder.setAESModeAndPadding(Mode.ECB);
        }

        log.info("解密后的客户端数据：{}", payload);

        Map<String, String> map = JSONUtil.toBean(payload, Map.class);

        // 根据解密后的参数重新构建请求
        StringBuilder stringBuilder = new StringBuilder(uri.getPath() + "?");
        try {
            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                stringBuilder.append(stringStringEntry.getKey())
                        .append("=")
                        .append(stringStringEntry.getValue())
                        .append("&");
            }

            uri = new URI(stringBuilder.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ServerHttpRequest newRequest = oldRequest.mutate().uri(uri).build();

//        gatewayContext.setFormData(queryParams);
//        exchange.getAttributes().put(GatewayContext.CACHE_GATEWAY_CONTEXT, gatewayContext);


        return chain.filter(exchange.mutate().request(newRequest).build());
    }

    /**
     * post put application 处理解密验签
     *
     * @param exchange
     * @param chain
     * @param oldRequest
     * @param uri
     * @param gatewayContext
     * @param userId
     * @return
     */
    private Mono<Void> getPostJsonVoidMono(ServerWebExchange exchange, GatewayFilterChain chain, ServerHttpRequest oldRequest, URI uri, GatewayContext gatewayContext, String userId) {
        byte[] decrypBytes;
        try {
            byte[] body = gatewayContext.getCacheBody().getBytes(StandardCharsets.UTF_8);
            String timestamp = exchange.getRequest().getHeaders().getFirst(ConstantFilter.TIMESTAMP);
            String algorithm = exchange.getRequest().getHeaders().getFirst(ConstantFilter.ALGORITHM);
            if (algorithm == null) {
                algorithm = SignAlgorithm.SHA256withRSA.getValue();
            }
            String sequenceId = exchange.getRequest().getHeaders().getFirst(ConstantFilter.SEQUENCE_ID);
            String clientType = exchange.getRequest().getHeaders().getFirst(ConstantFilter.CLIENT_TYPE);
            if (clientType == null) {
                clientType = "";
            }
            // 客户端传过来的数据
            String rootData = StrUtil.utf8Str(body);
            if ("IOS".equals(clientType)) {
                rootData = URLUtil.decode(rootData);
                if (rootData.startsWith("=")) {
                    rootData = StrUtil.sub(rootData, 1, rootData.length());
                }
            }

//            log.info("收到客户端请求参数：{}", rootData);
            JSONObject jsonObject = JSONUtil.parseObj(rootData);

            //通过sequenceId 获取调用方公钥//messageDigest
            String publicClientKey = cheshunKeyConfigProperties.getPublicKeys().get(sequenceId);
            //验签
            Sign sign = new Sign(algorithm, null, publicClientKey);

            //ASCII 排序
            Map<Object, Object> sort =
                    MapUtil.builder()
                            .put(ConstantFilter.SEQUENCE_ID, sequenceId)
                            .put(ConstantFilter.TIMESTAMP, timestamp)
                            .put(ConstantFilter.ALGORITHM, algorithm)
                            .put(ConstantFilter.CLIENT_TYPE, clientType)
                            .put(ConstantFilter.MESSAGE_DIGEST, jsonObject.getStr(ConstantFilter.MESSAGE_DIGEST))
                            .put(ConstantFilter.PAYLOAD, jsonObject.getStr(ConstantFilter.PAYLOAD))
                            .build();

            log.info("签名数据为：{}", sort);
            String sort1 = Permutation.sort(sort);
            log.info("排序后的签名数据：{}", sort1);
            boolean signature = sign.verify(StrUtil.bytes(sort1), Base64.decode(jsonObject.getStr(ConstantFilter.SIGNATURE)));
            if (!signature) {
                throw new RRException("验签失败");
            }

            log.info("验签成功：{}", Permutation.sort(sort));
            log.info("加密的payload：{}", jsonObject.getStr(ConstantFilter.PAYLOAD));
            //解密
            RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), cheshunKeyConfigProperties.getPrivateKey(), null);
            String messageDigest = rsa.decryptStr(jsonObject.getStr(ConstantFilter.MESSAGE_DIGEST), KeyType.PrivateKey);
            log.info("解密后的AES秘钥：{}", messageDigest);
            String payload = null;
            try {
                payload = new AES(Mode.CBC, Padding.PKCS5Padding, StrUtil.bytes(messageDigest), "0000000000000000".getBytes()).decryptStr(jsonObject.getStr(ConstantFilter.PAYLOAD));
                AESContextHolder.setAESModeAndPadding(Mode.CBC);
            } catch (Exception e) {
                payload = new AES(Mode.ECB, Padding.PKCS5Padding, StrUtil.bytes(messageDigest)).decryptStr(jsonObject.getStr(ConstantFilter.PAYLOAD));
                AESContextHolder.setAESModeAndPadding(Mode.ECB);
            }

            log.info("解密后的客户端数据：{}", payload);

            decrypBytes = StrUtil.bytes(payload);

        } catch (Exception e) {
            log.error("客户端数据解析异常:{}", e.toString());
            e.printStackTrace();
            throw new RRException(e.getMessage());
        }

        // 根据解密后的参数重新构建请求
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
        Flux<DataBuffer> bodyFlux = Flux.just(dataBufferFactory.wrap(decrypBytes));
        ServerHttpRequest newRequest = oldRequest.mutate().uri(uri).build();
        newRequest = new ServerHttpRequestDecorator(newRequest) {
            @Override
            public Flux<DataBuffer> getBody() {
                return bodyFlux;
            }

            @Override
            public MultiValueMap<String, String> getQueryParams() {
                return super.getQueryParams();
            }
        };

        // 构建新的请求头
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        if (StringUtils.hasText(userId)) {
            headers.set("UserId", userId);
        }

        // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
        int length = decrypBytes.length;
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        headers.setContentLength(length);
        log.info("后Header:{}", headers);
        // headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        newRequest = new ServerHttpRequestDecorator(newRequest) {
            @Override
            public HttpHeaders getHeaders() {
                return headers;
            }
        };

        // 把解密后的数据重置到exchange自定义属性中,在之后的日志GlobalLogFilter从此处获取请求参数打印日志
//        gatewayContext.setCacheBody(StrUtil.utf8Str(decrypBytes));
//        exchange.getAttributes().put(GatewayContext.CACHE_GATEWAY_CONTEXT, gatewayContext);

        return chain.filter(exchange.mutate().request(newRequest).build());
    }


    @Override
    public int getOrder() {
        return -25;
    }
}

