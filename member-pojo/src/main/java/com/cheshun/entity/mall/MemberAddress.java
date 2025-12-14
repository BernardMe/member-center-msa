package com.cheshun.entity.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 会员收货地址表
 *
 * @TableName member_address
 */
@TableName(value = "member_address")
@Data
public class MemberAddress {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员卡号
     */
    @TableField(value = "member_card_no")
    private String memberCardNo;

    /**
     * 收货人姓名
     */
    @TableField(value = "recipient_name")
    private String recipientName;

    /**
     * 收货人电话
     */
    @TableField(value = "recipient_phone")
    private String recipientPhone;

    /**
     * 收货地址
     */
    @TableField(value = "recipient_address")
    private String recipientAddress;

    /**
     * 详细地址
     */
    @TableField(value = "detail_address")
    private String detailAddress;

    @TableField(value = "lat")
    private Double lat;

    @TableField(value = "lng")
    private Double lng;

    /**
     * 邮政编码
     */
    @TableField(value = "postal_code")
    private String postalCode;

    /**
     * 地址标签
     */
    @TableField(value = "address_tag")
    private String addressTag;

    /**
     * 是否默认地址：0-否，1-是
     */
    @TableField(value = "is_default")
    private Integer isDefault;

    /**
     * 状态：0-删除，1-正常
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;

}