package com.cheshun.market.vo.admin.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminMallCouponListInfoVO {
    /**
     *ID
     */
    private Integer id;
    /**
     *名称
     */
    private String name;
    /**
     *名称
     */
    private String code;
    /**
     *创建时间
     */
    private String createTime;
    /**
     *显示标记
     */
    private String showTag;
    /**
     *状态
     */
    private String status;
    /**
     *领取时间段
     */
    private String getTime;
    /**
     *使用时间段
     */
    private String useTime;
    /**
     *优惠规则
     */
    private String rules;
    /**
     *券池
     */
    private String poolName;
}
