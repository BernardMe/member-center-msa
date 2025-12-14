package com.cheshun.entity.plantation;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: new_shop
 * @description: 任务信息表
 * @author: clf
 * @create: 2024-08-07 15:46
 **/
@Data
public class PlantationTaskDTO {
    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 任务类型 1-一次性任务 2-多次可完成任务
     */
    private Byte taskType;

    /**
     * 任务分组类型 1-下单 2-浏览 3-领取券 4-积分兑换
     */
    private Byte taskClass;

    /**
     * 任务分组类型名称
     */
    private String taskClassName;

    /**
     * 发放方式 1=单次发放，2=完成发放
     */
    private Byte issueType;

    /**
     * 任务提示
     */
    private String taskTips;

    /**
     * 完成所需次数
     */
    private Byte timeDripNumber;

    /**
     * 奖励水滴数
     */
    private Short awardDrip;

    /**
     * 图标url
     */
    private String iconUrl;

    /**
     * 是否显示图标
     */
    private Byte showIcon;

    /**
     * 任务名称
     */
    private String showTaskName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 启用状态(1:已启用, 0:未启用)
     */
    private Byte status;

    /**
     * 是否已删除(1:是, 0:否)
     */
    private Byte isDelete;

    /**
     * 是否快捷方式(1:是, 0:否)
     */
    private Byte isShortcut;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

