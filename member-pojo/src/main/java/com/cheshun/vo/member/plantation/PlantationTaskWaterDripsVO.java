package com.cheshun.vo.member.plantation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: newmallserver
 * @description: 未领取水滴信息
 * @author: clf
 * @create: 2024-09-06 16:12
 **/
@ApiModel(description = "任务水滴信息")
@Data
public class PlantationTaskWaterDripsVO implements Serializable {

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "用户任务id")
    private Integer userTaskId;

    @ApiModelProperty(value = "奖励水滴数")
    private Byte awardDrip;

    @ApiModelProperty(value = "任务提示")
    private String taskTrips;

    @ApiModelProperty(value = "植株id")
    private Integer plantId;
}
