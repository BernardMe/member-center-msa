package com.cheshun.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡号
     */
    private String memberCardNo;

    /**
     * 会员生日
     */
    private String birthday;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 会员性别 0 男 其余 女
     */
    private String gender;

    /**
     * 手机号
     */
    private String memberPhone;


    /**
     * 积分余额
     */
    private String pointsValue;


    /**
     * 服务专员编码
     */
    private String serviceWaiterCode;

    /**
     * 服务门店编码
     */
    private String serviceStoreCode;

    /**
     * 服务专员名称
     */
    private String serviceWaiterName;

    /**
     * 办卡店员名称
     */
    private String clerkName;

    /**
     * 办卡门店名称
     */
    private String storeName;

    /**
     * 服务门店名称
     */
    private String serviceStoreName;

}
