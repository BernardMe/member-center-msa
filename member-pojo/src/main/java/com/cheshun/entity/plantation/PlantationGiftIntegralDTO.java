package com.cheshun.entity.plantation;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: new_shop
 * @description: 百草园礼品积分实体类
 * @author: clf
 * @create: 2024-07-22 17:12
 **/
@Data
public class PlantationGiftIntegralDTO {
    /**
     * 礼品积分主键
     */
    private Long integralId;

    /**
     * 礼品积分值
     */
    private Double integralValue;

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
     * 创建人
     */
    private Integer createBy;

    /**
     * 更新人
     */
    private Integer updateBy;
}
