package com.cheshun.entity.admin.points;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 积分活动商品表
 *
 * @TableName points_goods_activities
 */
@TableName(value = "points_goods_activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsGoodsActivities {
    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    @TableId(type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    private Integer activitiesId;

    /**
     * 商品编号
     */
    @ApiModelProperty("商品编号")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 商品消耗积分
     */
    @ApiModelProperty("商品消耗积分")
    private BigDecimal goodsPoints;

    /**
     * 商品原价
     */
    @ApiModelProperty("商品原价")
    private BigDecimal goodsPrice;

    /**
     * 商品兑换优惠券
     */
    @ApiModelProperty("商品兑换优惠券")
    private String goodsCoupon;

    /**
     * 最大兑换数量（负数为不限制）
     */
    @ApiModelProperty("最大兑换数量（负数为不限制）")
    private Integer maxNumber;

    /**
     * 商品图片地址
     */
    @ApiModelProperty("商品图片地址")
    private String goodsImg;

    /**
     * 商品图片名称
     */
    @ApiModelProperty("商品图片名称")
    private String goodsImgName;

    /**
     * 商品介绍
     */
    @ApiModelProperty("商品介绍")
    private String goodsContent;

    /**
     * 商品优先级
     */
    @ApiModelProperty("商品优先级")
    private Integer goodsSort;

    /**
     * 商品库存
     */
    @ApiModelProperty("商品库存")
    private String goodsInventory;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime goodsCreatTime;

}