package com.cheshun.vo.member.activity.event;


import com.cheshun.entity.coupon.CouponInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "AdminLanterOptionVO", description = "展示的方案花灯配置信息")
public class AdminLanterOptionVO implements Serializable {


    @ApiModelProperty(value = "主题")
    private String topic;

    @ApiModelProperty(value = "一次游戏获取最大积分数量")
    private Integer maxPoint;

    @ApiModelProperty(value = "游戏时间")
    private Integer maxTime;

    @ApiModelProperty(value = "是否启用")
    private Integer isEnable;

    @ApiModelProperty(value = "图片链接")
    private String imagesUrl;

    @ApiModelProperty(value = "优惠券编码")
    private String fnumber;

    @ApiModelProperty(value = "优惠券列表")
    private List<CouponInfo> couponInfoList;
}
