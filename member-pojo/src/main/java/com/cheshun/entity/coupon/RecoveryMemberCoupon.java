package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryMemberCoupon {
    /**
     * 退回店员编号
     */
    private String clerkCode;
    /**
     * 退回店员名称
     */
    private String clerkName;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 门店编号
     */
    private String storeCode;
    /**
     * 退回门店名称
     */
    private String storeName;

    private String mallCouponNo;

}