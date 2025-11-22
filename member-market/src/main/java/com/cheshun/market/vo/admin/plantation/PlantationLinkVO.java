package com.cheshun.market.vo.admin.plantation;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: new_shop
 * @description: 任务链接
 * @author: clf
 * @create: 2024-08-09 11:38
 **/
@ApiModel(description = "任务链接信息")
@Data
public class PlantationLinkVO implements Serializable {

    @ApiModelProperty(value = "任务链接ID", required = true)
    private Integer linkId;

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "链接名称")
    private String linkName;

    @ApiModelProperty(value = "连接类型(0 内部 1 外部)")
    private Short linkType;

    @ApiModelProperty(value = "参数信息")
    private String param;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "首页模块主键")
    private Long mainModuleId;
}
