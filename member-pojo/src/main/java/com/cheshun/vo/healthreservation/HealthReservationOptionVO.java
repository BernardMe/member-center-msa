package com.cheshun.vo.healthreservation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "预约方案详情")
public class HealthReservationOptionVO {

    @ApiModelProperty(value = "方案编号")
    private String optionNo;

    @ApiModelProperty(value = "方案名称")
    private String proName;

    @ApiModelProperty(value = "方案内容")
    private String proComment;

    @ApiModelProperty(value = "是否计入资源")
    private String isResources;

    @ApiModelProperty(value = "项目id")
    private String proId;

    @ApiModelProperty(value = "项目周期")
    private String proCycle;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
