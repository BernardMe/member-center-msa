package com.cheshun.vo.project.customer.couplets;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "运营端-添加春联征集活动参数")
@Data
public class MemberCoupletsActVO implements Serializable {

    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动相关优惠券方案编号拼接串
     */
    private String couponNoStr;
    /**
     * 加工后券名称拼接串
     */
    private String manulNameStr;
    /**
     * 活动背景图片url
     */
    private String bgImgUrl;
    /**
     * 开始时间
     */
    private String startTimeStr;
    /**
     * 结束时间
     */
    private String endTimeStr;
    /**
     * 操作类型(1置为启用, 0置为禁用)
     */
    private Byte operType;
    /**
     * createTimeStr
     */
    private String createTimeStr;
    /**
     * updateTime
     */
    private String updateTimeStr;


}
