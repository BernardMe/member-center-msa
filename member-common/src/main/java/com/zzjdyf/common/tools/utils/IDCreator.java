package com.zzjdyf.common.tools.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 阿隆
 * Created on 2021/4/13 15:24.
 */
public class IDCreator {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 基于前缀+时间戳+随机字符串生成指定长度的id
     *
     * @param prefix 前缀
     * @return id
     */
    public static String id(String... prefix) {
        return timestampBased(20, prefix);
    }

    /**
     * 基于前缀+时间戳+随机字符串生成指定长度的流水号
     *
     * @param prefix 前缀
     * @return 流水号
     */
    public static String sn(String... prefix) {
        return datetimeBased(32, prefix);
    }

    /**
     * 基于前缀+时间戳+随机字符串生成指定长度的唯一标识
     *
     * @param length 长度
     * @param prefix 前缀
     * @return 唯一标识
     */
    private static String datetimeBased(int length, String... prefix) {
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            for (String item : prefix) {
                sb.append(item);
            }
        }
        sb.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        String data = sb.toString();
        if (length > data.length()) {
            data += generateNonceStr(length - data.length(), true);
        }
        return data;
    }

    /**
     * 基于前缀+时间戳+随机字符串生成指定长度的唯一标识
     *
     * @param length 长度
     * @param prefix 前缀
     * @return 唯一标识
     */
    private static String timestampBased(int length, String... prefix) {
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            for (String item : prefix) {
                sb.append(item);
            }
        }
        sb.append(System.currentTimeMillis());
        String data = sb.toString();
        if (length > data.length()) {
            data += generateNonceStr(length - data.length(), true);
        }
        return data;
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length  指定长度
     * @param onlyNum 仅数字
     * @return 随机数
     */
    private static String generateNonceStr(int length, boolean onlyNum) {
        String pool = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (onlyNum) {
            pool = "0123456789";
        }
        char[] nonceChars = new char[length];
        for (int index = 0; index < length; ++index) {
            nonceChars[index] = pool.charAt(RANDOM.nextInt(pool.length()));
        }
        return new String(nonceChars);
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 20; i++) {
                String id = sn("11", "10");
                set.add(id);
            }
            System.out.println(set.size());
            set.clear();
        }
    }
}
