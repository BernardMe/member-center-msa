//package com.zzjdyf.mall.feign.verify.shujubao.fallback;
//
//import com.zzjdyf.mall.feign.verify.shujubao.ShuJuBaoFeignClient;
//import com.zzjdyf.mall.feign.verify.shujubao.request.NamePhone2VerifyPostRequest;
//import com.zzjdyf.mall.feign.verify.shujubao.request.NamePhoneID3VerifyPostRequest;
//import com.zzjdyf.mall.feign.verify.shujubao.response.ShuJuBaoPostResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.net.URI;
//
///**
// * 功能描述 熔断降级操作feignClient
// *
// * @author xueqing wang
// * @date 2020/12/17 18:59
// */
//@Component
//@Slf4j
//public class ShuJuBaoFeignClientFallback implements ShuJuBaoFeignClient {
//
//    private final Throwable cause;
//
//    public ShuJuBaoFeignClientFallback(Throwable cause) {
//        this.cause = cause;
//    }
//
//    @Override
//    public ShuJuBaoPostResponse namePhone2Verify(URI uri, NamePhone2VerifyPostRequest namePhone2VerifyPostRequest) {
//        // 实现降级逻辑
//        ShuJuBaoPostResponse response = new ShuJuBaoPostResponse();
//        response.setSuccess(false);
//        response.setMessage("服务暂时不可用");
//        return response;
//    }
//
//    @Override
//    public ShuJuBaoPostResponse namePhoneId3Verify(URI uri, NamePhoneID3VerifyPostRequest namePhoneID3VerifyPostRequest) {
//        // 实现降级逻辑
//        ShuJuBaoPostResponse response = new ShuJuBaoPostResponse();
//        response.setSuccess(false);
//        response.setMessage("服务暂时不可用");
//        return response;
//    }
//}
