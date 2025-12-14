package com.cheshun.po.project.customer.couplets;

import lombok.Data;

import java.io.Serializable;

/**
 * 会员春联征集活动状态PO
 * @author wangzhuo
 */
@Data
public class MemberCoupletsRecordPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 会员卡号
     */
    private String memberCardNo;
    /**
     * 会员姓名
     */
    private String memberName;
    /**
     * 会员姓名
     */
    private String memberPhone;
    /**
     * 会员姓名
     */
    private String address;
    /**
     * 会员姓名
     */
    private String firstLine;
    /**
     * 会员姓名
     */
    private String secondLine;
    /**
     * 会员姓名
     */
    private String horizontalScroll;
    /**
     * 会员姓名
     */
    private String serviceWaiterCode;
    /**
     * 会员姓名
     */
    private String serviceWaiterName;
    /**
     * 会员姓名
     */
    private String serviceStoreCode;
    /**
     * 服务门店姓名
     */
    private String serviceStoreName;
    /**
     * 创建时间Str
     */
    private String createTimeStr;

}

