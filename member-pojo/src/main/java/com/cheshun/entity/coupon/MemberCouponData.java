package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * 云POS-查询会员持有优惠券信息返回信息
 * data信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCouponData {
    private Integer count;
    private ArrayList<MemberCouponRows> rows;
}
