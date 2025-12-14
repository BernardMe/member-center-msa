package com.cheshun.dto.healthreservation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("会员预约入参")
@Data
public class MemberBookingDTO {

    @ApiModelProperty(value = "方案编号")
    private String optionNo;

    @ApiModelProperty(value = "项目编码")
    private String proId;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;


}
