package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCouponListParam {
    /**
     * 优惠券方案列表
     */
    private String couponNo;
    /**
     * 优惠券编号列表
     * 优惠券发送后唯一标识
     */
    private ArrayList<String> memberCouponNoList;
    /**
     * 优惠券状态列表
     * 1：未生效；2.已生效,3.已过期,4.已核销，5.作废
     */
    private ArrayList<String> memberCouponStateList;
    /**
     * 会员卡号列表
     */
    private ArrayList<String> memberNoList;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 页数
     */
    private Integer pageStart;
}
