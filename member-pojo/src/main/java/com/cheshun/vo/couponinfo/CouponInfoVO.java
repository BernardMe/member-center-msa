package com.cheshun.vo.couponinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "分页查询优惠券信息")
public class CouponInfoVO implements Serializable {

    @ApiModelProperty(value = "优惠券id")
    private String fid;

    @ApiModelProperty(value = "优惠券编号")
    private String fnumber;

    @ApiModelProperty(value = "优惠券名称")
    private String fnameCn;

    @ApiModelProperty(value = "审核状态")
    private String fapproveState;

    @ApiModelProperty(value = "优惠券方案状态")
    private String fcouponState;

    @ApiModelProperty(value = "启用状态")
    private String fenableState;

    @ApiModelProperty(value = "优惠券数量 -1为不限制")
    private String fcouponQuantity;

    @ApiModelProperty(value = "会员领取数量 -1为不限制")
    private String fmemberLimitQuantity;

    @ApiModelProperty(value = "已领取数量")
    private String fhasGivenQuantity;

    @ApiModelProperty(value = "已使用数量")
    private String fhasUsedQuantity;

    @ApiModelProperty(value = "优惠券内容")
    private String fcouponContent;

    @ApiModelProperty(value = "删除状态")
    private String fdelFlag;

    @ApiModelProperty(value = "面额")
    private String fdenomination;


}
