package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "领取优惠券参数")
public class DrawCouponDTO implements Serializable {

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

//    @ApiModelProperty(value = "优惠券编码")
//    private String fnumber;

    @ApiModelProperty(value = "点击总数")
    private String clickNum;
}

