package com.zzjdyf.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.zzjdyf.auth.constant.AuthServerConstant;
import com.zzjdyf.auth.service.AuthSmsService;
import com.zzjdyf.auth.strategy.AuthSmsStrategy;
import com.zzjdyf.common.exception.BizCodeEnum;
import com.zzjdyf.common.result.Result;
import com.zzjdyf.common.tools.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信控制层
 *
 * @author xueqing wang
 * @date 2021年6月16日 10:40:19
 */
@RestController
@RequiredArgsConstructor
public class SMSController {

    @Value("${zzjdyf.sms.code.frequencyTime:60000}")
    private Double frequencyTime;

    /**
     * 认证厂商类别
     */
    @Value("${zzjdyf.sms.type}")
    String type;

    @Value("${zzjdyf.sms.codeContent}")
    String codeContent;

    /**
     * 短信服务策略器
     */
    private final AuthSmsStrategy authSmsStrategy;


    /**
     * redis组件
     */
    private final StringRedisTemplate redisTemplate;


    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @GetMapping("/sms/sendCode")
    public Result sendCode(@RequestParam("phone") String phone) {
        //接口防刷,在redis中缓存phone-code
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String prePhone = AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone;
        String v = ops.get(prePhone);
        if (!StringUtils.isEmpty(v)) {
            long pre = Long.parseLong(v.split("_")[1]);
            //如果存储的时间小于60s，说明60s内发送过验证码
            if (System.currentTimeMillis() - pre < frequencyTime) {
                return ResultUtil.result(String.valueOf(BizCodeEnum.SMS_CODE_EXCEPTION.getCode()), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        //如果存在的话，删除之前的验证码
        redisTemplate.delete(prePhone);
        //获取到6位数字的验证码
        String code = String.valueOf((int) ((Math.random() + 1) * 100000));
        //在redis中进行存储并设置过期时间
        ops.set(prePhone, code + "_" + System.currentTimeMillis(), 10, TimeUnit.MINUTES);
        String content = codeContent.replaceAll("000000", code);
        // 按权重随机
        AuthSmsService random = authSmsStrategy.random();
        Map map = random.sendMessage(phone, content);
        return random.buildResult(map);
    }


    /**
     * 验证短信验证码
     *
     * @param phone
     * @param code
     * @return
     */
    @GetMapping("/sms/verifyCode")
    public Boolean sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        //接口防刷,在redis中缓存phone-code
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String prePhone = AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone;
        String v = ops.get(prePhone);
        if (StrUtil.isBlank(v)) {
            return false;
        }
        redisTemplate.delete(prePhone);
        return StrUtil.equals(v.split("_")[0], code);
    }
}
