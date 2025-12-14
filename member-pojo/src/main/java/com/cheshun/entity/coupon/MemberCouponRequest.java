package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * 云POS-查询会员持有优惠券信息请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCouponRequest {
    /**
     * 优惠券方案编号
     */
    private String couponNo;
    /**
     * 会员券号
     * 单独查询券详情时传入
     */
    private ArrayList<String> memberCouponNoList;

    /**
     * 优惠券状态 1：未生效；2.已
     * 生效,3.已过期,4.已核销，5
     * 作废
     */
    private ArrayList<String> memberCouponStateList;

    /**
     * 会员卡号
     */
    private ArrayList<String> memberNoList;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     *分页页数
     */
    private Integer pageStart;
}
