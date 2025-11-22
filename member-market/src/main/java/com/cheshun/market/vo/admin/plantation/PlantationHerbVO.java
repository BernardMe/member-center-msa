package com.cheshun.market.vo.admin.plantation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: new_shop
 * @description: 百草园中药材类型
 * @author: clf
 * @create: 2024-07-25 15:46
 **/
@ApiModel(description = "中药材植株信息")
@Data
public class PlantationHerbVO {
    @ApiModelProperty(value = "中药材ID", required = true)
    private Integer herbId;

    @ApiModelProperty(value = "中药材名称", required = true)
    private String herbName;

    @ApiModelProperty(value = "中药材列表图片URL")
    private String herbImgUrl;

    @ApiModelProperty(value = "中药材详情图片URL")
    private String herbImgDetailUrl;

    @ApiModelProperty(value = "中药材详情")
    private String herbDetail;

    @ApiModelProperty(value = "阶段数量")
    private Byte stageNum;

    @ApiModelProperty(value = "启用状态(1:已启用, 0:未启用)")
    private Byte status;

    @ApiModelProperty(value = "是否已删除(1:是, 0:否)")
    private String isDelete;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "中药材植株种植顺序")
    private Integer herbSort;

    @ApiModelProperty(value = "阶段信息列表")
    private List<PlantationHerbStageVO> plantationPlantStageList;

    @ApiModelProperty(value = "中药材植株关联公告id")
    private Integer notificationId;
}
