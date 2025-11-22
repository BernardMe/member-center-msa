package com.zzjdyf.auth.controller;

import com.zzjdyf.auth.strategy.AuthVerifyStrategy;
import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class VerifyController {

    /**
     * 认证厂商类别
     */
    @Value("${zzjdyf.verify.type}")
    String type;

    /**
     * 认证服务策略器
     */
    private final AuthVerifyStrategy authVerifyStrategy;

    /**
     * 二要素验证
     * @param map
     * @return
     */
    @PostMapping("/verify/namePhone2Verify")
    public Map namePhone2Verify(@RequestBody Map map) {
        return authVerifyStrategy.getAuthVerifyService(type).namePhone2Verify(map);
    }


    /**
     * 三要素验证
     * @param map
     * @return
     */
    @PostMapping("/verify/namePhoneId3Verify")
    public Map namePhoneId3Verify(@RequestBody Map map) {
        return authVerifyStrategy.getAuthVerifyService(type).namePhoneId3Verify(map);
    }

    @PostMapping("/fileUpload")
    public Result fileUpload(HttpServletRequest request) {
        Map<String, Object> ok = new HashMap<>();
        ok.put("data", request.getHeader("UserId"));
        return ResultUtil.result(ok);
    }

}
