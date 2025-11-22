package com.cheshun.market.vo.admin.plantation;

/**
 * @program: new_shop
 * @description: 百草园礼品券实体类
 * @author: clf
 * @create: 2024-07-16 15:45
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@ApiModel(description = "礼品兑换券信息")
public class PlantationGiftCouponVO implements Serializable {

    @ApiModelProperty("礼品主键")
    private Long giftId;

    @ApiModelProperty("礼品名称")
    private String giftName;

    @ApiModelProperty("礼品主图片")
    private String mainImage;

    @ApiModelProperty("礼品副图片")
    private String secondaryImage;

    @ApiModelProperty("兑换券Code")
    private String couponCode;

    @ApiModelProperty("兑换券名称")
    private String couponName;

    @ApiModelProperty("礼品描述")
    private String giftDetail;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标记（0 正常 1已删除）")
    private Byte deleteFlag;

    @ApiModelProperty("删除时间")
    private LocalDateTime deleteTime;

    @ApiModelProperty("创建人")
    private Integer createBy;

    @ApiModelProperty("更新人")
    private Integer updateBy;
}
