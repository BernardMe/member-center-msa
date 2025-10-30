//package com.zzjdyf.market.feign.fallback;
//
//import com.zzjdyf.market.feign.GetwayFeignService;
//import feign.hystrix.FallbackFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.Map;
//
///**
// * @author 阿隆
// * Created on 2021/8/16 10:16 上午
// */
//@Slf4j
//@Component
//public class GetwayFallbackFactory implements FallbackFactory<GetwayFeignService> {
//    @Override
//    public GetwayFeignService create(Throwable throwable) {
//        log.error("GetwayFeignService", throwable);
//        return new GetwayFeignService() {
//            @Override
//            public Map<String, Object> queryFirstConsume(String etcNumber) {
//                return Collections.emptyMap();
//            }
//
//            @Override
//            public Map<String, Object> queryActiveCard(String channel, String stAccountNo) {
//                return Collections.emptyMap();
//            }
//
//            @Override
//            public Map<String, Object> queryTravelStats(Map<String, Object> body) {
//                return Collections.emptyMap();
//            }
//        };
//    }
//}
