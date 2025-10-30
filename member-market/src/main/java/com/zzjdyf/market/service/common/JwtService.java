package com.zzjdyf.market.service.common;

import cn.hutool.json.JSONUtil;
import com.zzjdyf.market.config.LoginConfig;
import com.zzjdyf.market.config.common.LoginInfo;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class JwtService {
    private final LoginConfig loginConfig;
    private final RedissonClient redissonClient;

    /**
     * 生成token
     *
     * @param loginInfo 登陆信息 {@link LoginInfo}
     * @return token token
     * @throws Exception 异常
     */
    public String generate(LoginInfo loginInfo) throws Exception {
        // 创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        // 将负载信息封装到Payload中
        Payload payload = new Payload(JSONUtil.toJsonStr(loginInfo));
        // 创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        // 创建HMAC签名器
        JWSSigner jwsSigner = new MACSigner(loginConfig.getToken().getSecretKey());
        // 签名
        jwsObject.sign(jwsSigner);
        String token = jwsObject.serialize();
        // token保存到缓存
        redissonClient.getBucket(loginConfig.getToken().getCachePrefix() + token).set(1, loginConfig.getToken().getCacheTtl().getSeconds(), TimeUnit.SECONDS);
        return token;
    }


    /**
     * 校验token并解析
     *
     * @param token token
     * @return loginInfo 登陆信息 {@link LoginInfo}
     * @throws Exception 异常
     */
    public LoginInfo verify(String token) throws Exception {
        // 从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        // 创建HMAC验证器
        JWSVerifier jwsVerifier = new MACVerifier(loginConfig.getToken().getSecretKey());
        if (!jwsObject.verify(jwsVerifier)) {
            throw new Exception("token不合法");
        }
        // 校验token是否有效
        if (!redissonClient.getBucket(loginConfig.getToken().getCachePrefix() + token).isExists()) {
            throw new Exception("token不存在");
        }
        // token合法且有效,解析token中的登陆信息
        String payload = jwsObject.getPayload().toString();
        LoginInfo loginInfo = JSONUtil.toBean(payload, LoginInfo.class, false);
        if (loginInfo == null) {
            delete(token);
            throw new Exception("token解析异常");
        }
        // 延长token有效期
        RBucket<String> bucket = redissonClient.getBucket(loginConfig.getToken().getCachePrefix() + token);
        bucket.expire(loginConfig.getToken().getCacheTtl().getSeconds(), TimeUnit.SECONDS);
        return loginInfo;
    }

    /**
     * 删除token
     *
     * @param token token
     */
    public void delete(String token) {
        redissonClient.getBucket(loginConfig.getToken().getCachePrefix() + token).delete();
    }
}
