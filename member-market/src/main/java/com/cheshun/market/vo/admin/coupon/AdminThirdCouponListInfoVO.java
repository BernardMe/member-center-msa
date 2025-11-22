package com.cheshun.market.vo.admin.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminThirdCouponListInfoVO {
    /**
     * 编码
     */
    private String couponCode;
    /**
     * 名称
     */
    private String couponName;
    /**
     * 类型
     */
    private String couponType;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 优惠金额
     */
    private Double couponAmount;
}
