package com.cheshun.vo.member.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoolCouponInfosVO {
    //券名
    private String couponName;
    //券规则
    private String couponRules;
    //券使用开始时间
    private LocalDateTime useBeginTime;
    //券使用结束时间
    private LocalDateTime useEndTime;
}
