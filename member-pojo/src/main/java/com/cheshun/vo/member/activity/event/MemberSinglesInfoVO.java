package com.cheshun.vo.member.activity.event;

import com.cheshun.dto.member.activity.event.UpvoteSingleDTO;
import com.cheshun.entity.member.activity.event.CommentContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "会员晒单信息")
public class MemberSinglesInfoVO implements Serializable {

    @ApiModelProperty(value = "会员晒单id")
    private String id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "会员名称")
    private String memberName;

    @ApiModelProperty(value = "会员推荐内容")
    private String reviewContent;

    @ApiModelProperty(value = "会员推荐图片链接")
    private List<String> reviewImg;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "晒单时间")
    private String reviewTime;

    @ApiModelProperty(value = "晒单点赞数")
    private Integer upvoteNums;

    @ApiModelProperty(value = "评论楼层")
    private String floor;

    @ApiModelProperty(value = "评论内容列表")
    private List<CommentContent> commentContents;

    @ApiModelProperty(value = "评论点赞列表")
    private List<UpvoteSingleDTO> upvoteSingleList;

    @ApiModelProperty(value = "是否点赞id 0未点赞 1点赞")
    private String isUpvote;

    @ApiModelProperty(value = "点赞id ")
    private String upvoteId;



}
