package com.zzjdyf.gateway.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.*;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzjdyf.common.Const;
import com.zzjdyf.gateway.constant.ConstantFilter;
import com.zzjdyf.gateway.service.RedisService;
import com.zzjdyf.gateway.util.AESContextHolder;
import com.zzjdyf.gateway.util.Permutation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

/**
 * Created by xueqing wang on 2021/4/14 18:11
 * 接口返回的数据进行加密,加签处理
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class RespEncryptFilter implements GlobalFilter, Ordered {

    private final CheshunKeyConfigProperties cheshunKeyConfigProperties;

    private final CheshunNoEncryptionPathsProperties cheshunNoEncryptionPathsProperties;

    private String tokenHeader = "token";


    private final RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest oldRequest = exchange.getRequest();
        URI uri = oldRequest.getURI();
        String token = oldRequest.getHeaders().getFirst(tokenHeader);
        //删除该交易
        Optional.ofNullable(token).ifPresent(s -> {
                    Boolean delete = redisService.del(s + "_" + uri.getPath());
                    log.info("删除redis交易:{},{}", s + "_" + uri.getPath(), delete);

                }
        );
        if (cheshunNoEncryptionPathsProperties.getPaths().stream().anyMatch(s -> uri.getPath().toLowerCase().contains(s))) {
            log.info("请求路径：{}，不进行加解密操作", uri.getPath());
            return chain.filter(exchange);
        }

        String algorithm = exchange.getRequest().getHeaders().getFirst(ConstantFilter.ALGORITHM);
        if (algorithm == null) {
            algorithm = SignAlgorithm.SHA256withRSA.getValue();
        }
        final String finalAlgorithm = algorithm;
        final String sequenceId = exchange.getRequest().getHeaders().getFirst(ConstantFilter.SEQUENCE_ID);
        ServerHttpResponse originalResponse = exchange.getResponse();
        originalResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.buffer().map(dataBuffer -> {

                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer join = dataBufferFactory.join(dataBuffer);

                        byte[] content = new byte[join.readableByteCount()];
                        join.read(content);
                        //释放掉内存
                        DataBufferUtils.release(join);
                        // 正常返回的数据
                        String rootData = StrUtil.utf8Str(content);
                        log.info("正常返回的数据:{}", rootData);
                        byte[] uppedContent = signAndEncrypt(rootData, sequenceId, finalAlgorithm);
                        DataBuffer db = bufferFactory.wrap(uppedContent);
                        originalResponse.getHeaders().setContentLength(db.readableByteCount());
                        return db;
                    }));
                }
                return super.writeWith(body);
            }
        };
        MDC.remove(Const.REQUEST_ID);

        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    private byte[] signAndEncrypt(String rootData, String sequenceId, String algorithm) {
        JSONObject jsonObject = JSONUtil.parseObj(rootData);

        byte[] respData = rootData.getBytes();
        //对数据进行加密 加签
        String clientPublicKey = cheshunKeyConfigProperties.getPublicKeys().get(sequenceId);

        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), null, clientPublicKey);
        //得到随机16位AES秘钥
        String messageDigest = RandomUtil.randomString(16);
        log.info("服务端未加密的AES秘钥：{}", messageDigest);
        //采用AES加密数据
        Mode mode = AESContextHolder.getAESModeAndPadding();
        String payload;
        if (mode == null || mode == Mode.CBC) {
            payload = new AES(Mode.CBC, Padding.PKCS5Padding, StrUtil.bytes(messageDigest), "0000000000000000".getBytes(StandardCharsets.UTF_8)).encryptBase64(rootData);
        } else {
            payload = new AES(Mode.ECB, Padding.PKCS5Padding, StrUtil.bytes(messageDigest)).encryptBase64(rootData);

        }

        //对AES秘钥进行RSA加密
        messageDigest = rsa.encryptBase64(messageDigest, KeyType.PublicKey);
        log.info("服务端经过RSA的AES秘钥：{}", messageDigest);

        //ASCII 排序
        Map<Object, Object> sort =
                MapUtil.builder()
                        .put(ConstantFilter.SEQUENCE_ID, sequenceId)
                        .put(ConstantFilter.JNL_ID, IdUtil.simpleUUID())
                        .put(ConstantFilter.CODE, StrUtil.nullToDefault(jsonObject.getStr(ConstantFilter.CODE), "200"))
                        .put(ConstantFilter.MESSAGE, StrUtil.nullToDefault(jsonObject.getStr(ConstantFilter.MESSAGE), "通讯成功"))
                        .put(ConstantFilter.ACTION, StrUtil.nullToDefault(jsonObject.getStr(ConstantFilter.ACTION), "0"))
                        .put(ConstantFilter.TIMESTAMP, DateUtil.now())
                        .put(ConstantFilter.ALGORITHM, algorithm)
                        .put(ConstantFilter.MESSAGE_DIGEST, messageDigest)
                        .put(ConstantFilter.PAYLOAD, payload)
                        .build();

        Sign sign = new Sign(algorithm, cheshunKeyConfigProperties.getPrivateKey(), null);
        String sort1 = Permutation.sort(sort);
        log.info("签名数据：{}", sort1);
        log.info("返回服务端数据：{}", sort);
        byte[] signature = sign.sign(StrUtil.bytes(sort1));
        sort.put("signature", Base64.encode(signature));
        // 加密后的数据返回给客户端
        byte[] uppedContent = StrUtil.bytes(StrUtil.utf8Str(JSONUtil.toJsonStr(sort)));
        return uppedContent;
    }

    @Override
    public int getOrder() {
        return -20;
    }

}

