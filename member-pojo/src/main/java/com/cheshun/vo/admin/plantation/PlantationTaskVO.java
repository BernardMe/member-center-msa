package com.cheshun.vo.admin.plantation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: new_shop
 * @description:
 * @author: clf
 * @create: 2024-08-09 10:32
 **/
@ApiModel(description = "任务信息")
@Data
public class PlantationTaskVO {

    @ApiModelProperty(value = "任务ID", required = true)
    private Integer taskId;

    @ApiModelProperty(value = "任务类型 1-一次性任务 2-多次可完成任务", required = true)
    private Byte taskType;

    @ApiModelProperty(value = "任务分组类型 1-下单 2-浏览 3-领取券 4-积分兑换 5-问卷", required = true)
    private Byte taskClass;

    @ApiModelProperty(value = "任务类型名称")
    private String taskClassName;

    @ApiModelProperty(value = "任务提示信息")
    private String taskTips;

    @ApiModelProperty(value = "完成所需次数")
    private Byte timeDripNumber;

    @ApiModelProperty(value = "发放方式 1=单次发放，2=完成发放")
    private Byte issueType;

    @ApiModelProperty(value = "奖励水滴数")
    private Short awardDrip;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图标URL")
    private String iconUrl;

    @ApiModelProperty(value = "是否显示图标 (1: 显示, 0: 不显示)")
    private Byte showIcon;

    @ApiModelProperty(value = "任务名称")
    private String showTaskName;

    @ApiModelProperty(value = "启用状态 (1: 已启用, 0: 未启用)")
    private Byte status;

    @ApiModelProperty(value = "是否已删除 (1: 是, 0: 否)")
    private Byte isDelete;

    @ApiModelProperty(value = "是否为快捷方式 (1: 是, 0: 否)")
    private Byte isShortcut;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "任务链接集合")
    private List<PlantationLinkVO> plantationLinks;
}
