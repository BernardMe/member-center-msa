package com.cheshun.entity.plantation;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: new_shop
 * @description: 中药材植株阶段
 * @author: clf
 * @create: 2024-07-25 15:47
 **/
@Data
public class PlantationHerbStageDTO implements Serializable {
    /**
     *阶段编号
     */
    private Integer stageId;
    /**
     *中药材类型编号
     */
    private Integer herbId;
    /**
     *阶段名称
     */
    private String stageName;
    /**
     *阶段图片
     */
    private String stageImage;
    /**
     *满成长值所需水滴
     */
    private Short dripCapacity;
    /**
     *满成长值所需浇水次数
     */
    private Short waterTimeCapacity;
    /**
     *启用状态(1:已启用, 0:未启用)
     */
    private Byte status;
    /**
     *是否已删除(1:是, 0:否)
     */
    private Byte isDelete;
    /**
     *创建时间
     */
    private LocalDateTime createTime;
    /**
     *更新时间
     */
    private LocalDateTime updateTime;
    /**
     *奖励id
     */
    private Long rewardId;
    /**
     *奖励类型（0券 1 积分）
     */
    private Byte rewardType;
    /**
     *奖励名称/积分数量
     */
    private String rewardName;
    /**
     * 阶段序号
     */
    private Integer stageSort;

    private static final long serialVersionUID = 1L;
}
