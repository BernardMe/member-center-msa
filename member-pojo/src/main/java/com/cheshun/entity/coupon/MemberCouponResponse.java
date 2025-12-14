package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 云POS-查询会员持有优惠券信息返回信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCouponResponse {
    /**
     * 业务码
     */
    private String bizCode;
    /**
     * 错误码
     */
    private String code;
    /**
     *数据
     */
    private MemberCouponData data;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 请求 traceId
     */
    private String tid;
}
