package com.cheshun.entity.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商城优惠券记录表
 *
 * @TableName mall_coupon_record
 */
@TableName(value = "mall_coupon_record")
@Data
public class MallCouponRecord {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员卡号
     */
    @TableField(value = "member_card_no")
    private String memberCardNo;

    /**
     * 优惠券方案编码
     */
    @TableField(value = "coupon_no")
    private String couponNo;

    /**
     * 优惠券编号
     */
    @TableField(value = "coupon_number")
    private String couponNumber;

    /**
     * 优惠券名称
     */
    @TableField(value = "coupon_name")
    private String couponName;

    /**
     * 券类型 1:满减 2:折扣 3:运费
     */
    @TableField(value = "coupon_type")
    private Integer couponType;

    /**
     * 优惠金额/折扣比例
     */
    @TableField(value = "amount")
    private Integer amount;

    /**
     * 最低消费金额
     */
    @TableField(value = "min_amount")
    private Integer minAmount;

    /**
     * 1:未使用 2:已锁定 3:已核销
     */
    @TableField(value = "coupon_status")
    private Integer couponStatus;

    /**
     * 是否禁用 1:是 0:否
     */
    @TableField(value = "disable")
    private Integer disable;

    /**
     * 是否已过期 1:是 0:否
     */
    @TableField(value = "is_expired")
    private Integer isExpired;

    /**
     * 领取时间
     */
    @TableField(value = "receive_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime receiveAt;

    /**
     * 有效期开始
     */
    @TableField(value = "valid_start")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime validStart;

    /**
     * 有效期结束
     */
    @TableField(value = "valid_end")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime validEnd;

    /**
     * 核销时间
     */
    @TableField(value = "use_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime useAt;

    /**
     * 退还时间
     */
    @TableField(value = "refund_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime refundAt;

    /**
     * 核销订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 核销人/收银员ID
     */
    @TableField(value = "use_by")
    private String useBy;

    /**
     * 核销门店ID
     */
    @TableField(value = "use_store_id")
    private String useStoreId;

    /**
     * 领取渠道备注
     */
    @TableField(value = "channel")
    private String channel;

    /**
     * 更新时间
     */
    @TableField(value = "update_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateAt;

    /**
     * 年月分区字段
     */
    @TableField(value = "select_year_month")
    private Integer selectYearMonth;
}