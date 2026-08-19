package com.cheshun.payment.service;

import com.cheshun.payment.dto.CreateOrderRequest;
import com.cheshun.payment.dto.CreateOrderResponse;
import com.cheshun.payment.dto.PaymentStatusDTO;

public interface OrderService {

    /** 客户端下单：创建本地订单 + 调用第三方统一下单，返回客户端拉起支付所需参数 */
    CreateOrderResponse createOrder(CreateOrderRequest request);

    /** 查询订单当前状态（读本地库，供客户端轮询用，不直接查第三方） */
    PaymentStatusDTO getOrderStatus(String orderNo);

    /** 处理第三方异步回调：更新订单状态为最终态 */
    void handleThirdPartyCallback(java.util.Map<String, String> callbackParams);

    /** 服务端主动回查第三方支付状态并更新本地订单（供定时任务/人工对账调用） */
    void syncStatusFromThirdParty(String orderNo);
}
