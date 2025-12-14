package com.cheshun.entity.coupon;

import lombok.Data;

import java.io.Serializable;

@Data
public class CouponInfo implements Serializable {

    private String fid;

    private String fnumber;

    private String fnameCn;

    private String fapproveState;

    private String fcouponState;

    private String fenableState;

    private String fcouponQuantity;

    private String fmemberLimitQuantity;

    private String fhasGivenQuantity;

    private String fhasUsedQuantity;

    private String fuseStartTime;

    private String fcouponContent;

    private String fdelFlag;

    private String fdenomination;


}
