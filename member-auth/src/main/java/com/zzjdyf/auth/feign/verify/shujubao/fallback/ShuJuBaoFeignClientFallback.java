package com.zzjdyf.auth.feign.verify.shujubao.fallback;

import com.zzjdyf.auth.feign.verify.shujubao.ShuJuBaoFeignClient;
import com.zzjdyf.auth.feign.verify.shujubao.request.NamePhone2VerifyPostRequest;
import com.zzjdyf.auth.feign.verify.shujubao.request.NamePhoneID3VerifyPostRequest;
import com.zzjdyf.auth.feign.verify.shujubao.response.ShuJuBaoPostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * 功能描述 熔断降级操作feignClient
 *
 * @author xueqing wang
 * @date 2020/12/17 18:59
 */
@Component
@Slf4j
public class ShuJuBaoFeignClientFallback implements ShuJuBaoFeignClient {


    @Override
    public ShuJuBaoPostResponse namePhone2Verify(URI uri, NamePhone2VerifyPostRequest namePhone2VerifyPostRequest) {
        return null;
    }

    @Override
    public ShuJuBaoPostResponse namePhoneId3Verify(URI uri, NamePhoneID3VerifyPostRequest namePhoneID3VerifyPostRequest) {
        return null;
    }
}
