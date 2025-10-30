package com.zzjdyf.market.service.common;

import cn.hutool.core.util.IdUtil;
import com.zzjdyf.common.exception.BizCodeEnum;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.CaptchaConfig;
import com.zzjdyf.market.vo.dto.CaptchaDto;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Service
@AllArgsConstructor
public class CaptchaService {
    private final CaptchaConfig captchaConfig;
    private final RedissonClient redissonClient;

    /**
     * 获取验证码
     *
     * @return 验证码信息
     */
    public CaptchaDto generate() {
        try {
            Captcha captcha = generateCaptcha();
            // 当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
            String captchaValue = captcha.text();
            if (captcha.getCharType() - 1 == CaptchaConfig.CaptchaType.arithmetic.ordinal() && captchaValue.contains(".")) {
                captchaValue = captchaValue.split("\\.")[0];
            }
            // 保存到缓存
            String uuid = IdUtil.simpleUUID();
            redissonClient.getBucket(captchaConfig.getCachePrefix() + uuid).set(captchaValue, captchaConfig.getExpiration(), TimeUnit.MINUTES);
            return CaptchaDto.builder().id(uuid).image(captcha.toBase64()).build();
        } catch (Exception e) {
            throw new RRException(BizCodeEnum.GET_CAPTCHA_FAILED, e);
        }
    }

    /**
     * 验证验证码是否正确
     *
     * @param uuid  验证码id
     * @param value 输入的验证码
     */
    public void verify(String uuid, String value) {
        // 从缓存获取验证码信息
        Object obj = redissonClient.getBucket(captchaConfig.getCachePrefix() + uuid).get();
        if (obj == null || !value.equals(obj.toString())) {
            throw new RRException(BizCodeEnum.CAPTCHA_INCORRECT);
        }
    }

    /**
     * 生成验证码
     *
     * @return 验证码信息 {@link Captcha}
     */
    private Captcha generateCaptcha() throws Exception {
        Captcha captcha;
        synchronized (this) {
            switch (captchaConfig.getType()) {
                case arithmetic:
                    // 算术类型
                    captcha = captchaConfig.arithmeticCaptcha();
                    // 几位数运算，默认是两位
                    captcha.setLen(captchaConfig.getLength());
                    break;
                case chinese:
                    captcha = new ChineseCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    captcha.setLen(captchaConfig.getLength());
                    break;
                case chinese_gif:
                    captcha = new ChineseGifCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    captcha.setLen(captchaConfig.getLength());
                    break;
                case gif:
                    captcha = new GifCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    captcha.setLen(captchaConfig.getLength());
                    break;
                case spec:
                    captcha = new SpecCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    captcha.setLen(captchaConfig.getLength());
                    break;
                default:
                    throw new Exception("验证码配置信息错误！正确配置查看 LoginCodeEnum ");
            }
        }
        if (StringUtils.hasText(captchaConfig.getFontName())) {
            captcha.setFont(new Font(captchaConfig.getFontName(), Font.PLAIN, captchaConfig.getFontSize()));
        }
        return captcha;
    }
}
