package com.cheshun.vo.mall.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberAddressPO {
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String recipientName;

    /**
     * 收货人电话
     */
    @ApiModelProperty(value = "收货人电话")
    private String recipientPhone;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String recipientAddress;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String detailAddress;

    @ApiModelProperty(value = "lat")
    private Double lat;

    @ApiModelProperty(value = "lng")
    private Double lng;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    private String postalCode;

    /**
     * 地址标签
     */
    @ApiModelProperty(value = "地址标签'home', 'company', 'school', 'other'")
    private String addressTag;

    /**
     * 是否默认地址：0-否，1-是
     */
    @ApiModelProperty(value = "是否默认地址：0-否，1-是")
    private Integer isDefault;

    /**
     * 状态：0-删除，1-正常
     */
    @ApiModelProperty(value = "状态：0-删除，1-正常")
    private Integer status;

}