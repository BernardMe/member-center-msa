package com.cheshun.market.vo.admin.plantation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: new_shop
 * @description: 中药材植株阶段
 * @author: clf
 * @create: 2024-07-25 15:47
 **/
@ApiModel(description = "种植阶段信息")
@Data
public class PlantationHerbStageVO {
    //0 券 1 积分
    public static final byte COUPON = 0;
    public static final byte INTEGRAL = 1;

    @ApiModelProperty(value = "阶段编号")
    private Integer stageId;

    @ApiModelProperty(value = "中药材类型编号")
    private Integer herbId;

    @ApiModelProperty(value = "阶段名称")
    private String stageName;

    @ApiModelProperty(value = "阶段图片")
    private String stageImage;

    @ApiModelProperty(value = "满成长值所需水滴")
    private Short dripCapacity;

    @ApiModelProperty(value = "满成长值所需浇水次数")
    private Short waterTimeCapacity;

    @ApiModelProperty(value = "奖励id")
    private Long rewardId;

    @ApiModelProperty(value = "奖励类型（0 券 1 积分）")
    private Byte rewardType;

    @ApiModelProperty(value = "奖励名称/积分数量")
    private String rewardName;

    @ApiModelProperty(value = "启用状态(1:已启用, 0:未启用)")
    private Byte status;

    @ApiModelProperty(value = "是否已删除(1:是, 0:否)")
    private Byte isDelete;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "阶段序号")
    private Integer stageSort;
}
