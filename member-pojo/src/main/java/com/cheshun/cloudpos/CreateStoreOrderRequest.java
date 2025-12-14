package com.cheshun.cloudpos;

import lombok.*;

import java.util.List;

/**
 * 云pos-订单下账-请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateStoreOrderRequest {
    /**
     * 企业号
     */
    private String entId;

    /**
     * 订单主体
     */
    private CreateStoreOrder order;

    /**
     * 订单明细
     */
    private List<CreateStoreOrderDetail> orderDetail;

    /**
     * 订单支付方式
     */
    private List<CreateStoreOrderFee> orderFee;

//    private List<CreateStoreOrderGiftAmount> orderGiftAmount;

    /**
     * 处方信息
     */
    private List<CreateStoreOrderRx> orderRx;

}
