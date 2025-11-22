package com.cheshun.market.feign;

import com.cheshun.common.tools.utils.R;
//import com.zzjdyf.market.feign.fallback.AuthFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 阿隆
 * Created on 2021/4/29 11:40 上午
 */
//@FeignClient(value = "member-auth", fallbackFactory = AuthFallbackFactory.class)
@FeignClient(value = "member-auth")
public interface AuthFeignService {
    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return 发送结果
     */
    @GetMapping("/sms/sendCode")
    R<Object> sendCode(@RequestParam("phone") String phone);

    /**
     * 验证短信验证码
     *
     * @param phone 手机号
     * @param code  短信验证码
     * @return 验证结果
     */
    @GetMapping("/sms/verifyCode")
    Boolean verifyCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
