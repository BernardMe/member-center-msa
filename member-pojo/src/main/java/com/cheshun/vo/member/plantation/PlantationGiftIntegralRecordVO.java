package com.cheshun.vo.member.plantation;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: new_shop
 * @description: 积分发放记录实体类
 * @author: clf
 * @create: 2024-07-23 10:41
 **/
@Data
public class PlantationGiftIntegralRecordVO {
    /**
     * 积分记录主键
     */
    private Long recordId;

    /**
     * 礼品积分ID
     */
    private Long integralId;

    /**
     * 礼品积分值
     */
    private Double integralValue;

    /**
     * 种植中药材编号
     */
    private Integer herbId;

    /**
     * 植株编号
     */
    private Integer plantId;

    /**
     * 阶段ID
     */
    private Integer stageId;

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

}
