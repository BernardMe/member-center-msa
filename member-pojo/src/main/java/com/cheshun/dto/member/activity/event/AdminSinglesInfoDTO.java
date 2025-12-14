package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "管理端查看会员晒单查询条件")
@Data
public class AdminSinglesInfoDTO {

    @ApiModelProperty(value = "起始页")
    private String page;

    @ApiModelProperty(value = "每页条数")
    private String pageSize;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "晒单时间")
    private String reviewTime;
}
