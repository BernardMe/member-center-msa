package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@ApiModel(description = "点赞所需参数")
@Data
public class UpvoteSingleDTO implements Serializable {

    @ApiModelProperty(value = "点赞id")
    private String upvoteId;

    @ApiModelProperty(value = "晒单id")
    private String singleId;

    @ApiModelProperty(value = "点赞会员卡号")
    private String upvoteCardNo;

    @ApiModelProperty(value = "点赞会员名称")
    private String upvoteName;

    @ApiModelProperty(value = "点赞会员手机号")
    private String phone;

    @ApiModelProperty(value = "是否点赞 1:点赞 0:不点赞")
    private String isUpvote;


}
