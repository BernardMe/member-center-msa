package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WriteOffCoupon {
    /**
     * 使用渠道编号 1：ERP、 2：微商城、3：医馆、4： 手动核销
     */
    private Integer channelCode;

    private String mallCouponNo;

    private List<WriteOffCouponLockShareRequestList> writeOffCouponLockShareRequestList;

    @Data
    public static class WriteOffCouponLockShareRequestList {
        /**
         * 核销数量
         */
        private Integer couponQuantity;
        /**
         * 锁库 id，释放/核销会员 优惠券时，不能为空
         */
        private String lockUid;
        /**
         * 会员优惠券编号，不能 为空
         */
        private String memberCouponNo;
        /**
         * 订单号
         */
        private String orderNo;
        /**
         * 门店
         */
        private String storeCode;
        /**
         * 门店名称
         */
        private String storeName;
        /**
         * 门店编号
         */
        private String storeNo;
    }
}

