package com.cheshun.common.tools.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author 阿隆
 * Created on 2021/4/13 15:54.
 */
public class EncryptUtils {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) throws Exception {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        String data = "测试数据";
        String encryptData = encryptBase64(data.getBytes(StandardCharsets.UTF_8));
        // Base64 常见于邮件\http加密.
        System.out.println("Base64加密:" + encryptData);
        System.out.println("Base64解密:" + new String(decryptBase64(encryptData), StandardCharsets.UTF_8));
        // MD5 常用于文件校验
        System.out.println("MD5加密:" + md5(data));
        // SHA 被广泛地应用于电子商务等信息安全领域,数字签名等密码学应用中重要的工具
        System.out.println("SHA-1加密:" + sha(data, SHA.SHA_1));
        System.out.println("SHA-224加密:" + sha(data, SHA.SHA_224));
        System.out.println("SHA-256加密:" + sha(data, SHA.SHA_256));
        System.out.println("SHA-384加密:" + sha(data, SHA.SHA_384));
        System.out.println("SHA-512加密:" + sha(data, SHA.SHA_512));
        // Hmac 防止数据伪造,鉴别消息的完整性
        String key = generateNonceStr();
        System.out.println("HmacMD5加密:" + hmac(data, key, HMAC.HmacMD5));
        System.out.println("HmacSHA1加密:" + hmac(data, key, HMAC.HmacSHA1));
        System.out.println("HmacSHA256加密:" + hmac(data, key, HMAC.HmacSHA256));
        System.out.println("HmacSHA384加密:" + hmac(data, key, HMAC.HmacSHA384));
        System.out.println("HmacSHA512加密:" + hmac(data, key, HMAC.HmacSHA512));
        // AES 对称加密算法
        key = generateNonceStr(16);
        String randomStr = generateNonceStr(16);
        String appId = "mp31361641948141";
        encryptData = encryptAes(data, key, randomStr, appId);
        System.out.println("AES加密:" + encryptData);
        System.out.println("AES解密:" + decryptAes(encryptData, key, randomStr, appId));

        System.out.println("***************综合应用场景(参考微信支付)***************");
        // 综合应用场景(参考微信支付,未用到Hmac验签)
        // 后台提供了一个API: /api/test
        // 前端调用该API: /api/test
        //
        // 前端调用前准备数据:
        long userId = 100001L;
        System.out.println("用户id:" + userId);
        // AES加密Key(16位),前后端需保持一致
        String aesKey = "sjdkf1827394ig73";
        // 明文,真正需要提交的重要数据
        Map<String, Object> importantDataMap = new HashMap<>();
        importantDataMap.put("userId", userId);
        importantDataMap.put("userName", "allon");
        String importantData = new ObjectMapper().writeValueAsString(importantDataMap);
        System.out.println("传递的真实数据:" + importantData);
        // 前端调用时参数信息:
        // timeStamp 时间戳(秒)
        long timeStamp = System.currentTimeMillis() / 1000;
        // nonceStr 随机字符串(16位)
        String nonceStr = generateNonceStr(16);
        // ciphertext 前端把明文加密后的密文，使用AES加密
        String ciphertext = encryptAes(importantData, aesKey, nonceStr, String.valueOf(userId));
        // sign 前端签名信息(使用字段唯一id(比如用户id、账户id、appId等)、timeStamp、nonceStr、ciphertext计算得出的签名值),使用SHA进行签名
        // 签名字符串拼接格式:
        // 签名串一共有四行，每一行为一个参数。
        // 行尾以\n（换行符，ASCII编码值为0x0A）结束，包括最后一行。
        // 如果参数本身以\n结束，也需要附加一个\n
        String signatureStr = userId + "\n" + timeStamp + "\n" + nonceStr + "\n" + ciphertext + "\n";
        String sign = sha(signatureStr, SHA.SHA_256);
        String digest = hmac(sign, key, HMAC.HmacSHA256);
        System.out.println("签名信息:" + sign);
        // 前端POST方式访问API: /api/test
        String body = "{" +
                "\"userId\":" + userId + "," +
                "\"timeStamp\":" + timeStamp + "," +
                "\"nonceStr\":\"" + nonceStr + "\"," +
                "\"ciphertext\":\"" + ciphertext + "\"," +
                "\"digest\":\"" + digest + "\"" +
                "}";
        System.out.println("前端POST方式访问API时发送的body:" + body);

        // 后端接收前端发送的body后处理:
        // 从body中解析出userId、timeStamp、nonceStr、ciphertext、sign
        // 1.验证签名
        // 后端根据得到的userId、timeStamp、nonceStr、ciphertext生成新签名信息
        String newSignatureStr = userId + "\n" + timeStamp + "\n" + nonceStr + "\n" + ciphertext + "\n";
        String newSign = sha(newSignatureStr, SHA.SHA_256);
        String newDigest = hmac(newSign, key, HMAC.HmacSHA256);
        if (newDigest.equals(digest)) {
            System.out.println("前端传递的签名与后端生成的一致,准备对密文解密");
            String decryptData = decryptAes(ciphertext, aesKey, nonceStr, String.valueOf(userId));
            System.out.println("解密出的明文:" + decryptData);
        }
    }

    /*
     **********************************************
     * Base64
     * 用来把任意序列的8位字节描述为一种不易被人直接识别的形式.
     * 常见于邮件\http加密.
     **********************************************
     */

    /**
     * Base64加密
     *
     * @param data 原内容
     * @return 密文
     */
    public static String encryptBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * Base64解密
     *
     * @param data 解密内容
     * @return 原内容
     */
    public static byte[] decryptBase64(String data) {
        return Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
    }

    /*
     **********************************************
     * MD5,message-digest algorithm 5,信息-摘要算法
     * 保证数据的完整性.
     * 广泛用于加密和解密技术,常用于文件校验.
     * 不管文件多大,经过MD5后都能生成唯一的MD5值.
     **********************************************
     */

    /**
     * MD5加密
     *
     * @param data 明文
     * @return 密文
     * @throws Exception 异常
     */
    public static String md5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }

    /*
     **********************************************
     * SHA,Secure Hash Algorithm,安全散列算法
     * 不可逆,不可以从消息摘要中复原信息.
     * 两个不同的消息,不会产生同样的消息摘要.
     * 保证数据的完整性.
     * 数字签名等密码学应用中重要的工具,被广泛地应用于电子商务等信息安全领域.
     * 虽然SHA与MD5通过碰撞法都被破解了,但是SHA仍然是公认的安全加密算法,较之MD5更为安全.
     **********************************************
     */

    /**
     * SHA加密
     *
     * @param data      明文
     * @param algorithm 加密算法
     * @return 密文
     * @throws Exception 异常
     */
    public static String sha(String data, SHA algorithm) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(algorithm.getName());
        byte[] array = sha.digest(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }

    /*
     **********************************************
     * HMAC,Hash Message Authentication Code,散列消息鉴别码,基于密钥的Hash算法的认证协议
     * 防止数据伪造.
     * 消息鉴别码实现鉴别的原理是,用公开函数和密钥产生一个固定长度的值作为认证标识,用这个标识鉴别消息的完整性.
     * 使用一个密钥生成一个固定大小的小数据块,即MAC,并将其加入到消息中,然后传输.
     * 接收方利用与发送方共享的密钥进行鉴别认证等.
     **********************************************
     */

    /**
     * HMAC加密
     *
     * @param data      明文
     * @param key       密钥
     * @param algorithm 加密算法
     * @return 密文
     * @throws Exception 异常
     */
    public static String hmac(String data, String key, HMAC algorithm) throws Exception {
//        // 1、创建密钥生成器
//        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.getName());
//        // 2、产生密钥
//        SecretKey secretKey = keyGenerator.generateKey();
//        // 3、获取密钥
//        byte[] key = secretKey.getEncoded();
        // 1、还原密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm.getName());
        // 2、创建MAC对象
        Mac mac = Mac.getInstance(algorithm.getName());
        // 3、设置密钥
        mac.init(secretKeySpec);
        // 4、数据加密
        byte[] array = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }

    /*
     **********************************************
     * AES,Advanced Encryption Standard,高级加密标准
     * 对称加密算法(加密和解密用到的密钥是相同的)
     **********************************************
     */

    /**
     * AES加密
     *
     * @param data 明文
     * @param key  密钥
     * @return 密文
     * @throws Exception 异常
     */
    public static String encryptAes(String data, String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        if (key.length() % 16 != 0) {
            return null;
        }
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(data.getBytes("utf-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * AES加密
     *
     * @param data      明文
     * @param key       密钥
     * @param randomStr 随机数
     * @param idData    唯一标识(用户id,appId等)
     * @return 密文
     * @throws Exception 异常
     */
    public static String encryptAes(String data, String key, String randomStr, String idData) throws Exception {
        // Aes key
        byte[] aesKeyBytes = key.getBytes(StandardCharsets.UTF_8);
//        byte[] aesKeyBytes = Base64.getDecoder().decode();
        // 设置解密模式为AES的CBC模式
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(aesKeyBytes, 0, 16);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        ByteGroup byteCollector = new ByteGroup();
        byte[] randomStrBytes = randomStr.getBytes(StandardCharsets.UTF_8);
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] networkBytesOrder = getNetworkBytesOrder(dataBytes.length);
        byte[] idDataBytes = idData.getBytes(StandardCharsets.UTF_8);
        // randomStr + networkBytesOrder + text
        byteCollector.addBytes(randomStrBytes);
        byteCollector.addBytes(networkBytesOrder);
        byteCollector.addBytes(dataBytes);
        byteCollector.addBytes(idDataBytes);
        // ... + pad: 使用自定义的填充方式对明文进行补位填充
        byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
        byteCollector.addBytes(padBytes);
        // 获得最终的字节流, 未加密
        byte[] unencrypted = byteCollector.toBytes();
        // 加密
        byte[] encrypted = cipher.doFinal(unencrypted);
        // 使用BASE64对加密后的字符串进行编码
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * 对密文进行解密.
     *
     * @param data 需要解密的密文
     * @param key  密钥
     * @return 解密得到的明文
     * @throws Exception aes解密失败
     */
    public static String decryptAes(String data, String key) throws Exception {
        // 判断Key是否正确
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        // 判断Key是否为16位
        if (key.length() % 16 != 0) {
            return null;
        }
        byte[] raw = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = Base64.getDecoder().decode(data);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, StandardCharsets.UTF_8);
    }

    /**
     * 对密文进行解密.
     *
     * @param data      需要解密的密文
     * @param key       密钥
     * @param randomStr 随机字符串
     * @param idData    唯一标识(用户id,appId等)
     * @return 解密得到的明文
     * @throws Exception aes解密失败
     */
    public static String decryptAes(String data, String key, String randomStr, String idData) throws Exception {
        // Aes key
        byte[] aesKeyBytes = key.getBytes(StandardCharsets.UTF_8);
        // 设置解密模式为AES的CBC模式
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(aesKeyBytes, 0, 16);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

        // 使用BASE64对密文进行解码
        byte[] encrypted = Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
        // 解密
        byte[] original = cipher.doFinal(encrypted);
        // 去除补位字符
        byte[] bytes = PKCS7Encoder.decode(original);
        // 分离16位随机字符串,网络字节序和idData
        // 16位随机字符串
        String newRandomStr = new String(Arrays.copyOfRange(bytes, 0, 16), StandardCharsets.UTF_8);
        if (!newRandomStr.equals(randomStr)) {
            throw new Exception("无效的随机字符串");
        }
        // 网络字节序
        byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);
        int xmlLength = recoverNetworkBytesOrder(networkOrder);
        // 解密内容
        String content = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), StandardCharsets.UTF_8);
        // AppId
        String newIdData = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length), StandardCharsets.UTF_8);
        if (!newIdData.equals(idData)) {
            throw new Exception("无效的唯一标识");
        }
        return content;
    }

    /**
     * 使用*号加密字符串指定区间的字符
     *
     * @param data       待加密字符串
     * @param startIndex 加密开始位置(从0开始)
     * @param endIndex   加密结束位置(包括该位置)
     * @return 加密后字符串
     */
    public static String encryptStringWithStar(String data, int startIndex, int endIndex) {
        startIndex = Math.max(0, startIndex);
        startIndex = startIndex >= data.length() ? 0 : startIndex;
        if (endIndex <= startIndex || endIndex >= data.length()) {
            endIndex = data.length() - 1;
        }
        char star = '*';
        String head = data.substring(0, startIndex);
        String tail = data.substring(endIndex + 1);
        char[] chars = new char[endIndex + 1 - startIndex];
        Arrays.fill(chars, star);
        return head + new String(chars) + tail;
    }

    /**
     * 生成随机数
     *
     * @return 随机数
     */
    private static String generateNonceStr() {
        return generateNonceStr(32);
    }

    /**
     * 生成随机数
     *
     * @param length 指定长度
     * @return 随机数
     */
    private static String generateNonceStr(int length) {
        char[] nonceChars = new char[length];
        for (int index = 0; index < length; ++index) {
            nonceChars[index] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(RANDOM.nextInt("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length()));
        }
        return new String(nonceChars);
    }

    // 生成4个字节的网络字节序
    private static byte[] getNetworkBytesOrder(int sourceNumber) {
        byte[] orderBytes = new byte[4];
        orderBytes[3] = (byte) (sourceNumber & 0xFF);
        orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
        orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
        orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
        return orderBytes;
    }

    // 还原4个字节的网络字节序
    private static int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }

    private static class ByteGroup {
        ArrayList<Byte> byteContainer = new ArrayList<>();

        public byte[] toBytes() {
            byte[] bytes = new byte[byteContainer.size()];
            for (int i = 0; i < byteContainer.size(); i++) {
                bytes[i] = byteContainer.get(i);
            }
            return bytes;
        }

        public void addBytes(byte[] bytes) {
            for (byte b : bytes) {
                byteContainer.add(b);
            }
        }

        public int size() {
            return byteContainer.size();
        }
    }

    /**
     * 提供基于PKCS7算法的加解密接口.
     */
    private static class PKCS7Encoder {
        static int BLOCK_SIZE = 32;

        /**
         * 获得对明文进行补位填充的字节.
         *
         * @param count 需要进行填充补位操作的明文字节个数
         * @return 补齐用的字节数组
         */
        static byte[] encode(int count) {
            // 计算需要填充的位数
            int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
            if (amountToPad == 0) {
                amountToPad = BLOCK_SIZE;
            }
            // 获得补位所用的字符
            char padChr = chr(amountToPad);
            StringBuilder tmp = new StringBuilder();
            for (int index = 0; index < amountToPad; index++) {
                tmp.append(padChr);
            }
            return tmp.toString().getBytes(StandardCharsets.UTF_8);
        }

        /**
         * 删除解密后明文的补位字符
         *
         * @param decrypted 解密后的明文
         * @return 删除补位字符后的明文
         */
        static byte[] decode(byte[] decrypted) {
            int pad = decrypted[decrypted.length - 1];
            if (pad < 1 || pad > 32) {
                pad = 0;
            }
            return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
        }

        /**
         * 将数字转化成ASCII码对应的字符,用于对明文进行补码
         *
         * @param a 需要转化的数字
         * @return 转化得到的字符
         */
        static char chr(int a) {
            byte target = (byte) (a & 0xFF);
            return (char) target;
        }
    }

    public enum SHA {
        /**
         * SHA-1
         */
        SHA_1("SHA-1"),
        /**
         * SHA-224
         */
        SHA_224("SHA-224"),
        /**
         * SHA-256
         */
        SHA_256("SHA-256"),
        /**
         * SHA-384
         */
        SHA_384("SHA-384"),
        /**
         * SHA-512
         */
        SHA_512("SHA-512");

        private final String name;

        SHA(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum HMAC {
        /**
         * HmacMD5
         */
        HmacMD5("HmacMD5"),
        /**
         * HmacSHA1
         */
        HmacSHA1("HmacSHA1"),
        /**
         * HmacSHA256
         */
        HmacSHA256("HmacSHA256"),
        /**
         * HmacSHA384
         */
        HmacSHA384("HmacSHA384"),
        /**
         * HmacSHA512
         */
        HmacSHA512("HmacSHA512");

        private final String name;

        HMAC(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}

