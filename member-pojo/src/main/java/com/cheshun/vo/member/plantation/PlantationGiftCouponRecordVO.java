package com.cheshun.vo.member.plantation;

/**
 * @program: new_shop
 * @description: 百草园礼品发放记录表
 * @author: clf
 * @create: 2024-07-16 15:54
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(description = "礼品发放记录")
@Data
public class PlantationGiftCouponRecordVO implements Serializable {
    //是否已兑换
    public static final byte IS_REDEEMED = 1;
    public static final byte IS_NOT_REDEEMED = 0;
    public static final byte IS_EXPIRED = 2;
    //0 券 1 积分
    public static final byte COUPON = 0;
    public static final byte INTEGRAL = 1;

    @ApiModelProperty(value = "礼品发放主键")
    private Long distributionId;

    @ApiModelProperty(value = "礼品主键（礼品表关联）")
    private Long giftId;

    @ApiModelProperty(value = "种植中药材编号")
    private Integer herbId;

    @ApiModelProperty(value = "植株编号")
    private Integer plantId;

    @ApiModelProperty(value = "用户会员卡号code")
    private String vipCardNo;

    @ApiModelProperty(value = "兑换券Code")
    private String couponCode;

    @ApiModelProperty(value = "阶段ID")
    private Integer stageId;

    @ApiModelProperty(value = "兑换时间")
    private LocalDateTime redemptionTime;

    @ApiModelProperty(value = "是否已兑换（0 未兑换 1 已兑换 2 已过期）默认0")
    private Byte redeemed;

    @ApiModelProperty(value = "创建时间（发放时间）")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标记（0 正常 1 已删除）")
    private Byte deleteFlag;

    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deleteTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    //*******礼品券信息*******

    @ApiModelProperty(value = "礼品主图片")
    private String mainImage;

    @ApiModelProperty(value = "礼品副图片")
    private String secondaryImage;

    @ApiModelProperty(value = "礼品描述")
    private String giftDetail;

    @ApiModelProperty(value = "礼品积分值")
    private Double integralValue;

    @ApiModelProperty(value = "奖励类型（0 券 1 积分）")
    private Byte rewardType;

    @ApiModelProperty(value = "礼品名称")
    private String giftName;
}


