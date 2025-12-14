package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

@ApiOperation(value = "管理端查看接龙方案")
@Data
public class AdminSolitaireOptionDTO implements Serializable {

    @ApiModelProperty(value = "起始页")
    private String page;

    @ApiModelProperty(value = "每页条数")
    private String pageSize;

    @ApiModelProperty(value = "是否启用")
    private String isEnable;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "活动标题")
    private String eventsTitle;
}
