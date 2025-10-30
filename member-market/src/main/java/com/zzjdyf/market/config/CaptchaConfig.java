package com.zzjdyf.market.config;

import com.wf.captcha.ArithmeticCaptcha;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 *
 * @author 阿隆
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "captcha")
public class CaptchaConfig {
    /**
     * 验证码配置
     */
    private CaptchaType type;
    /**
     * 验证码有效期 分钟
     */
    private Long expiration = 2L;
    /**
     * 验证码内容长度
     */
    private int length = 2;
    /**
     * 验证码宽度
     */
    private int width = 111;
    /**
     * 验证码高度
     */
    private int height = 36;
    /**
     * 验证码字体
     */
    private String fontName;
    /**
     * 字体大小
     */
    private int fontSize = 25;
    /**
     * 验证码缓存前缀
     */
    private String cachePrefix;

    /**
     * 验证码类型
     *
     * @author 阿隆
     */
    public enum CaptchaType {
        /**
         * 算数
         */
        arithmetic,
        /**
         * 中文
         */
        chinese,
        /**
         * 中文闪图
         */
        chinese_gif,
        /**
         * 闪图
         */
        gif,
        spec
    }

    public ArithmeticCaptcha arithmeticCaptcha() {
        return new ArithmeticCaptcha(getWidth(), getHeight()) {
            @Override
            protected char[] alphas() {
                // 生成随机数字和运算符
                int n1 = num(1, 10), n2 = num(1, 10);
                int opt = num(3);
                // 计算结果
                int res = new int[]{n1 + n2, n1 - n2, n1 * n2}[opt];
                // 转换为字符运算符
                char optChar = "+-x".charAt(opt);
                this.setArithmeticString(String.format("%s%c%s=?", n1, optChar, n2));
                this.chars = String.valueOf(res);
                return chars.toCharArray();
            }
        };
    }
}
