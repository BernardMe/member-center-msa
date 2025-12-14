package com.cheshun.vo.admin.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateCouponRequestVO {
    /**
     * 优惠券ID
     */
    private Integer couponId;
    /**
     * 显示标记
     */
    private String showTag;
    /**
     * 删除标记
     */
    private String deleteTag;
}
