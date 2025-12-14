package com.cheshun.vo.activity.diabetes;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DiabetesShareLikesVO {
    @ApiModelProperty(value = "分享作品Id")
    private Integer recordId;
    @ApiModelProperty(value = "点赞人会员卡号")
    private String likesMemberCardNo;
    @ApiModelProperty(value = "点赞类型：1-点赞，2-评论，3-转发，4-投票")
    private String likesType;
    @ApiModelProperty(value = "评论内容")
    private String comment;
}
