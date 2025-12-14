package com.cheshun.vo.admin.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCouponPoolDetailVO {
    /**
     * id
     */
    private Integer poolId;
    /**
     * 编号
     */
    private String poolCode;
    /**
     * 名称
     */
    private String poolName;
    /**
     * 图片url
     */
    private String pictureUrl;
    /**
     * 领取限制类型
     */
    private String limitType;
}
