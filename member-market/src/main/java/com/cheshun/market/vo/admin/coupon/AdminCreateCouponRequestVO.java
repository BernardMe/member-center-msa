package com.cheshun.market.vo.admin.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateCouponRequestVO {
    /**
     * 优惠券编号
     */
    @NotBlank(message = "优惠券编号不能为空")
    private String couponCode;
    /**
     * 优惠券名称
     */
    @NotBlank(message = "优惠券名称不能为空")
    private String couponName;
    /**
     * 领取开始时间
     */
    @NotNull(message = "领取开始时间不能为空")
    private String startTimeStr;
    /**
     * 领取结束时间
     */
    @NotNull(message = "领取结束时间不能为空")
    private String endTimeStr;
    /**
     * 券池编号
     */
    @NotBlank(message = "券池编号不能为空")
    private String couponPoolCode;
    /**
     * 券池名称
     */
    @NotBlank(message = "券池名称不能为空")
    private String couponPoolName;
    /**
     * 单人限领次数
     */
    @NotNull(message = "单人限领次数不能为空")
    private Integer limitNumber;
    /**
     * 优惠券库存
     */
    @NotNull(message = "优惠券库存不能为空")
        private Integer couponStock;
}
