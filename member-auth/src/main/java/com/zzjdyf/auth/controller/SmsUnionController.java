package com.zzjdyf.auth.controller;

import com.zzjdyf.auth.service.AuthSmsService;
import com.zzjdyf.auth.strategy.AuthSmsStrategy;
import com.zzjdyf.auth.vo.SmsSingleVo;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 短信公共控制层
 *
 * @author wangzhuo
 * @date 20210902
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SmsUnionController {


    /**
     * 短信服务策略器
     */
    private final AuthSmsStrategy authSmsStrategy;
    @Autowired
    @Qualifier("smsUnionExecutor")
    private final ThreadPoolExecutor smsUnionExecutor;

    /**
     * 多渠道短信权重接口
     *
     * @param param 短信参数 {@link SmsSingleVo}
     * @return Result 返回信息 {@link Result}
     */
    @PostMapping("/smsUnion/send")
    public Result sendCode(@RequestBody SmsSingleVo param) throws ExecutionException, InterruptedException {
        //参数校验
        if (null == param || StringUtils.isEmpty(param.getMobiles()) || StringUtils.isEmpty(param.getContents())) {
            throw new RRException("发送短信参数异常");
        }
        AuthSmsService random = authSmsStrategy.random();
        //利用线程
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        CompletableFuture<Map<String, Object>> completableFuture2= CompletableFuture.supplyAsync(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            Map<String, Object> map = random.sendMessage(param.getMobiles(), param.getContents());
            return map;
        }, smsUnionExecutor);
        return random.buildResult(completableFuture2.get());
    }

}
