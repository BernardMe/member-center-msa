package com.cheshun.dto.member.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "评论内容")
@Data
public class CommentContentDTO implements Serializable {

    @ApiModelProperty(value = "评论会员卡号")
    private String commentCardNo;

    @ApiModelProperty(value = "评论会员名称")
    private String commentName;

    @ApiModelProperty(value = "评论会员手机号")
    private String phone;

    @ApiModelProperty(value = "评论内容")
    private String followContent;

    @ApiModelProperty(value = "晒单id")
    private String singleId;
}
