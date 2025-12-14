package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "接收会员此次点击次数并添加相应积分")
public class MemberGainPointDTO implements Serializable {

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "点击总数")
    private Integer clickNum;
}
