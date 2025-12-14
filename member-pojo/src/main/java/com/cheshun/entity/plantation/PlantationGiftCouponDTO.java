package com.cheshun.entity.plantation;

/**
 * @program: new_shop
 * @description: 百草园礼品券实体类
 * @author: clf
 * @create: 2024-07-16 15:45
 **/
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PlantationGiftCouponDTO implements Serializable {

    /**
     * 礼品主键
     */
    private Long giftId;

    /**
     * 礼品名称
     */
    private String giftName;

    /**
     * 礼品主图片
     */
    private String mainImage;

    /**
     * 礼品副图片
     */
    private String secondaryImage;

    /**
     * 兑换券Code
     */
    private String couponCode;

    /**
     * 兑换券名称
     */
    private String couponName;

    /**
     * 礼品描述
     */
    private String giftDetail;

    /**
     * 创建时间
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
     * 创建人id
     */
    private Integer createBy;

    /**
     * 更新人id
     */
    private Integer updateBy;

}
