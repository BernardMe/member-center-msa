package com.cheshun.vo.member.activity.event;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "后台晒单信息")
public class AdminMemberSinglesInfoVO implements Serializable {

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "会员名称")
    private String memberName;

    @ApiModelProperty(value = "会员推荐内容")
    private String reviewContent;

    @ApiModelProperty(value = "会员推荐图片链接")
    private String reviewImages;

    @ApiModelProperty(value = "会员晒单id")
    private List<String> reviewImgs;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "晒单时间")
    private String reviewTime;

    @ApiModelProperty(value = "晒单点赞数")
    private Integer upvoteNums;

    @ApiModelProperty(value = "评论楼层")
    private String floor;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "地址")
    private String address;


}
