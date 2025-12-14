package com.cheshun.entity.member.activity.event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberSolitaireOption implements Serializable {

    @ApiModelProperty(value = "方案id")
    private String eventsId;

    @ApiModelProperty(value = "方案标题")
    private String eventsTitle;

    @ApiModelProperty(value = "活动时间描述")
    private String eventsTimeDescribe;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "活动规则")
    private String eventsRule;

    @ApiModelProperty(value = "活动内容说明")
    private String eventsExplain;

    @ApiModelProperty(value = "活动奖励说明")
    private String eventsRewards;

    @ApiModelProperty(value = "关注事项")
    private String followContent;

    @ApiModelProperty(value = "小程序码")
    private String eventsProgramCode;

    @ApiModelProperty(value = "活动图片")
    private String eventsImage;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改时间")
    private String modifyTime;

    @ApiModelProperty(value = "是否启用 1:启用 0:未启用")
    private String isEnable;
}
