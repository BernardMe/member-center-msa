package com.cheshun.vo.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "会员累计获取积分")
public class MemberTotalPointsVO implements Serializable {

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "会员累计获取积分")
    private Integer pointNums;

    @ApiModelProperty(value = "会员分享次数")
    private Integer shareNums;

    @ApiModelProperty(value = "会员剩余次数")
    private Integer remain;

    @ApiModelProperty(value = "会员是否领券")
    private String isHasCoupon;




}
