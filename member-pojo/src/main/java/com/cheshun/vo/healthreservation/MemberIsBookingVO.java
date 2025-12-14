package com.cheshun.vo.healthreservation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "会员预约详情")
public class MemberIsBookingVO {
    @ApiModelProperty(value = "预约日期")
    private String reserDate;

    @ApiModelProperty(value = "预约时间范围")
    private String scopeTime;

    @ApiModelProperty(value = "预约人员")
    private String reserName;

    @ApiModelProperty(value = "预约门店")
    private String storeName;

    @ApiModelProperty(value = "预约门店编号")
    private String storeCode;

    @ApiModelProperty("预约项目名称")
    private String proName;

    @ApiModelProperty("预约项目内容")
    private String proComment;
}
