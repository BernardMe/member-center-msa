package com.zzjdyf.market.service.common;

import com.zzjdyf.common.annotation.MockData;
import com.zzjdyf.common.exception.BizCodeEnum;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.common.tools.utils.R;
import com.zzjdyf.market.feign.AuthFeignService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author 阿隆
 * Created on 2021/7/12 8:53 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class SmsService {
    private final AuthFeignService authFeignService;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     */
    @MockData(profiles = {"dev", "test"}, returnType = Void.class)
    public void send(String phone) {
        R<Object> r = authFeignService.sendCode(phone);
        if (r.getCode() != HttpStatus.OK.value()) {
            throw new RRException(r.getMessage());
        }
    }

    /**
     * 检验短信验证码
     *
     * @param phone   手机号
     * @param smsCode 短信验证码
     */
    @MockData(profiles = {"dev", "test"}, returnType = Void.class)
    public void verify(String phone, String smsCode) {
        Boolean result = authFeignService.verifyCode(phone, smsCode);
        if (!Optional.ofNullable(result).orElse(false)) {
            throw new RRException(BizCodeEnum.CREATE_EXPIRED);
        }
    }
}
