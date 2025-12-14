package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分享会员与被分享会员")
public class MemberShareDTO {

    @ApiModelProperty(value = "分享会员")
    private String shareMember;

    @ApiModelProperty(value = "被分享会员")
    private String receiveMember;
}
