package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockCoupon {
    private String memberCouponNo;
    private OrderInfoShareRequest orderInfoShareRequest;

    @Data
    public static class OrderInfoShareRequest {
        private String memberNo;
        private String orderNo;
        private Integer actualAmount;
        private String channelCode;
        private String clerkCode;
        private String clerkName;
        private Integer discountAmount;
        private String queryDate;
        private Integer shareType;
        private String storeAreaCode;
        private String storeCode;
        private String storeName;
        private String storeType;
        private List<OrderGoods> orderGoodsList;
    }

    @Data
    public static class OrderGoods {
        private Integer discountAmount;
        private Integer discountPrice;
        private String goodsNo;
        private Integer goodsQuantity;
        private String goodsUid;
        private Integer shareAmount;
        private Integer sharePrice;
        private Integer sort;
        private String typeCode;
    }
}
