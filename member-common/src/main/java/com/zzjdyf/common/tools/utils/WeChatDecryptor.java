package com.zzjdyf.common.tools.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Slf4j
public class WeChatDecryptor {

    public static String decrypt(String encryptedData, String iv, String sessionKey) throws Exception {
        // 1. 参数校验
        Objects.requireNonNull(encryptedData, "encryptedData不能为null");
        Objects.requireNonNull(iv, "iv不能为null");
        Objects.requireNonNull(sessionKey, "sessionKey不能为null");
        if (sessionKey.length() != 24) {
            throw new IllegalArgumentException("sessionKey长度无效");
        }

        try {
            // 2. Base64解码
            byte[] dataByte = Base64.getDecoder().decode(encryptedData);
            byte[] ivByte = Base64.getDecoder().decode(iv);
            byte[] keyByte = Base64.getDecoder().decode(sessionKey);

            // 3. AES-CBC解密
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivByte);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decryptedByte = cipher.doFinal(dataByte);

            // 4. 验证结果
            String result = new String(decryptedByte, "UTF-8");
            if (!result.contains("\"phoneNumber\"")) {
                throw new Exception("解密结果无效: " + result);
            }
            return result;

        } catch (IllegalArgumentException e) {
            throw new Exception("Base64解码失败", e);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new Exception("系统加密配置错误", e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new Exception("无效的密钥或IV", e);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception("解密失败，请检查参数", e);
        }
    }


    public static String decryptGroup(String encryptedData, String iv, String sessionKey) throws Exception {
        byte[] dataByte = Base64.getDecoder().decode(encryptedData);  // 解码encryptedData
        byte[] ivByte = Base64.getDecoder().decode(iv);              // 解码iv
        byte[] keyByte = Base64.getDecoder().decode(sessionKey);

        // 初始化AES解密
        SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivByte);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // 执行解密
        byte[] decryptedByte = cipher.doFinal(dataByte);
        return new String(decryptedByte, "UTF-8"); // 返回JSON字符串
    }
}