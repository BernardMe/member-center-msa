package com.cheshun.entity.plantation;

/**
 * @program: new_shop
 * @description: 百草园礼品发放记录表
 * @author: clf
 * @create: 2024-07-16 15:54
 **/
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PlantationGiftCouponRecordDTO implements Serializable {


    /**
     * 礼品发放主键
     */
    private Long distributionId;

    /**
     * 礼品主键（礼品表关联）
     */
    private Long giftId;

    /**
     * 种植中药材编号
     */
    private Integer herbId;

    /**
     * 植株编号
     */
    private Integer plantId;

    /**
     * 用户会员卡号code
     */
    private String vipCardNo;

    /**
     * 兑换券Code
     */
    private String couponCode;


    /**
     * 阶段ID
     */
    private Integer stageId;

    /**
     * 兑换时间
     */
    private LocalDateTime redemptionTime;

    /**
     * 是否已兑换（0 未兑换 1 已兑换）默认0
     */
    private Byte redeemed;

    /**
     * 创建时间（发放时间）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记（0 正常 1已删除）
     */
    private Byte deleteFlag;

    /**
     * 删除时间
     */
    private LocalDateTime deleteTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    //*******礼品券信息*******

    /**
     * 礼品主图片
     */
    private String mainImage;

    /**
     * 礼品副图片
     */
    private String secondaryImage;

    /**
     * 礼品名称
     */
    private String giftName;
}

