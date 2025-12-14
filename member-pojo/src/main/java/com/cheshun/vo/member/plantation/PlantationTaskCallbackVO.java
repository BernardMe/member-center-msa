package com.cheshun.vo.member.plantation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 任务回调更新状态参数VO
 * @Author wangzhuo
 * @Date 20240822
 **/
@Data
public class PlantationTaskCallbackVO implements Serializable {
    //是否已兑换
    public static final byte IS_REDEEMED = 1;
    public static final byte IS_NOT_REDEEMED = 0;

    //0 券 1 积分
    public static final byte COUPON = 0;
    public static final byte INTEGRAL = 1;

    /**
     * 任务主键
     */
    private Long taskId;
    /**
     * 用户任务id
     */
    private Integer userTaskId;
    /**
     * 用户id
     */
    private String userId;

    @ApiModelProperty(value = "用户会员卡号code")
    private String vipCardNo;
    /**
     * 已完成次数
     */
    private Integer completeNum;
}


