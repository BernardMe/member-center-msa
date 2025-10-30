//package com.zzjdyf.market.feign.fallback;
//
//import com.zzjdyf.common.tools.utils.R;
//import com.zzjdyf.market.feign.AuthFeignService;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @author 阿隆
// * Created on 2021/4/29 11:41 上午
// */
//@Component
//public class AuthFallbackFactory implements FallbackFactory<AuthFeignService> {
//    @Override
//    public AuthFeignService create(Throwable throwable) {
//        return new AuthFeignService() {
//            @Override
//            public R<Object> sendCode(String phone) {
//                return R.error("获取验证码失败");
//            }
//
//            @Override
//            public Boolean verifyCode(String phone, String code) {
//                return false;
//            }
//        };
//    }
//}
