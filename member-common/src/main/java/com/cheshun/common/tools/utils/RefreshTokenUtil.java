package com.cheshun.common.tools.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RefreshTokenUtil {

    private static final String HMAC_ALGORITHM = "HmacSHA256"; // 加密算法
    private static final String SECRET_KEY = "zzj-fresh-token"; // 自定义密钥

    public static String encryptOpenId(String openId) {
        try {
            Mac hmac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
            hmac.init(keySpec);
            byte[] hash = hmac.doFinal(openId.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("加密失败", e);
        }
    }
}
