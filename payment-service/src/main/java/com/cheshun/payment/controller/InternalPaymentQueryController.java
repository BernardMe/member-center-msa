package com.cheshun.payment.controller;

import com.cheshun.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ============================================================
 * 服务端对接第三方支付：主动查询接口（内部/运维使用，客户端不可见）
 * 场景：客户端支付了但没收到第三方回调（网络抖动/第三方延迟），
 *      客服/风控后台手动触发一次强制回查，或供定时任务调用。
 * 部署建议：放在内网网关后面，或加运维鉴权，不对公网客户端开放。
 * ============================================================
 */
@RestController
@RequiredArgsConstructor
public class InternalPaymentQueryController {

    private final OrderService orderService;

    /** 手动触发一次"服务端 -> 第三方"的状态回查，并同步更新本地订单 */
    @PostMapping("/api/internal/payment/{orderNo}/sync")
    public void forceSyncFromThirdParty(@PathVariable String orderNo) {
        orderService.syncStatusFromThirdParty(orderNo);
    }
}
