package com.cheshun.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会员查询相关参数")
public class MemberInfoQueryDTO {

    @ApiModelProperty("会员卡号")
    private String memberCardNo;

    @ApiModelProperty("会员手机号")
    private String memberPhone;
}
