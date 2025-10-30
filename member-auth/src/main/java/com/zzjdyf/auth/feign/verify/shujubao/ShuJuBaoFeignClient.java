package com.zzjdyf.auth.feign.verify.shujubao;

import com.zzjdyf.auth.feign.verify.shujubao.fallback.ShuJuBaoFeignClientFallback;
import com.zzjdyf.auth.feign.verify.shujubao.request.NamePhone2VerifyPostRequest;
import com.zzjdyf.auth.feign.verify.shujubao.request.NamePhoneID3VerifyPostRequest;
import com.zzjdyf.auth.feign.verify.shujubao.response.ShuJuBaoPostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

/**
 * 功能描述 数据包认证服务feignClient
 *
 * @author xueqing wang
 * @date 2020/12/17 18:58
 */
@FeignClient(name="shujubao-server", url="url-placeholder", fallback = ShuJuBaoFeignClientFallback.class)
public interface ShuJuBaoFeignClient {



    /**
     * 功能描述: 二要素认证
     *
     * @param namePhone2VerifyPostRequest 请求信息
     * @return OCRGetResponse
     * @author xueqing wang
     * @date 2020/12/16 10:41
     */
    @PostMapping(value="/9296")
    public ShuJuBaoPostResponse namePhone2Verify(URI uri, @RequestBody NamePhone2VerifyPostRequest namePhone2VerifyPostRequest);


    /**
     * 功能描述: 三要素认证
     *
     * @param namePhoneID3VerifyPostRequest 请求信息
     * @return OCRGetResponse
     * @author xueqing wang
     * @date 2020/12/16 10:41
     */
    @PostMapping(value="/1979")
    public ShuJuBaoPostResponse namePhoneId3Verify(URI uri, @RequestBody NamePhoneID3VerifyPostRequest namePhoneID3VerifyPostRequest);
}
