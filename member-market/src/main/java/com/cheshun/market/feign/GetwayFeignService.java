package com.cheshun.market.feign;

//import com.zzjdyf.market.feign.fallback.GetwayFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 阿隆
 * Created on 2021/8/16 10:16 上午
 */
//@FeignClient(value = "getway", fallbackFactory = GetwayFallbackFactory.class)
@FeignClient(value = "getway")
public interface GetwayFeignService {
    /**
     * 查询指定ECT账户的首次消费记录
     *
     * @param etcNumber ECT账户
     * @return 首次消费记录 {@link Map}
     */
    @PostMapping("getway/MarketQryTravel.do")
    Map<String, Object> queryFirstConsume(@RequestParam("etcNumber") String etcNumber);

    /**
     * 查询激活的速通ETC卡号/obu标签号
     *
     * @param channel     激活渠道
     * @param stAccountNo ECT账户
     * @return 激活的速通ETC卡号/obu标签号 {@link Map}
     */
    @PostMapping("getway/QrySTEtcNumber.do")
    Map<String, Object> queryActiveCard(@RequestParam(value = "channel", required = false) String channel,
                                        @RequestParam("stAccountNo") String stAccountNo);

    /**
     * 查询代理商累计推广用户的通行笔数和交易金额
     *
     * @param body 参数
     * @return 通行笔数和交易金额 {@link Map}
     */
    @PostMapping("getway/EtcMarketTravel.do")
    Map<String, Object> queryTravelStats(@RequestBody Map<String, Object> body);
}
