package com.cheshun.entity.member.activity.event;


import com.cheshun.dto.member.activity.event.UpvoteSingleDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MemberCommentsInfo implements Serializable {
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

    @ApiModelProperty(value = "晒单图片")
    private String reviewImages;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "晒单时间")
    private String reviewTime;

    @ApiModelProperty(value = "修改时间")
    private String modifytime;

    @ApiModelProperty(value = "楼层")
    private String floor;

    @ApiModelProperty(value = "评论点赞数")
    private Integer upvoteNums;

    @ApiModelProperty(value = "方案编号")
    private String eventsId;

    /**
     * 评论数据
     */
    private List<CommentContent> commentContentList;

    /**
     * 点赞数据
     */
    private List<UpvoteSingleDTO> upvoteSingleList;
}
