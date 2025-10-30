//package com.zzjdyf.market.rest.admin;
//
//import com.zzjdyf.common.annotation.NoToken;
//import com.zzjdyf.market.feign.GetwayFeignService;
//import io.swagger.annotations.Api;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
///**
// * @author 阿隆
// * Created on 2021/9/6 11:26 上午.
// */
//@RestController
//@RequestMapping("clsapi/market/etc/feign/getway")
//@Api(tags = "后管系统-GetWay API")
//@AllArgsConstructor
//public class FeignTestController {
//    private final GetwayFeignService getwayFeignService;
//
//    /**
//     * 查询指定ECT账户的首次消费记录
//     *
//     * @param etcNumber ECT账户
//     * @return 首次消费记录 {@link Map}
//     */
//    @NoToken
//    @PostMapping("queryFirstConsume")
//    public Map<String, Object> queryFirstConsume(@RequestParam("etcNumber") String etcNumber) {
//        return getwayFeignService.queryFirstConsume(etcNumber);
//    }
//
//    /**
//     * 查询激活的速通ETC卡号/obu标签号
//     *
//     * @param channel     激活渠道
//     * @param stAccountNo ECT账户
//     * @return 激活的速通ETC卡号/obu标签号 {@link Map}
//     */
//    @NoToken
//    @PostMapping("queryActiveCard")
//    public Map<String, Object> queryActiveCard(@RequestParam(value = "channel", required = false) String channel,
//                                               @RequestParam("stAccountNo") String stAccountNo) {
//        return getwayFeignService.queryActiveCard(channel, stAccountNo);
//    }
//
//    /**
//     * 查询代理商累计推广用户的通行笔数和交易金额
//     *
//     * @param body 参数
//     * @return 通行笔数和交易金额 {@link Map}
//     */
//    @NoToken
//    @PostMapping("queryTravelStats")
//    public Map<String, Object> queryTravelStats(@RequestBody Map<String, Object> body) {
//        return getwayFeignService.queryTravelStats(body);
//    }
//}
