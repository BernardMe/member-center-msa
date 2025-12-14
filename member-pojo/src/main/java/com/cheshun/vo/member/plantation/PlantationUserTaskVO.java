package com.cheshun.vo.member.plantation;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户任务参数VO
 * @Author wangzhuo
 * @Date 20240831
 **/
@Data
public class PlantationUserTaskVO implements Serializable {

    /**
     * 任务主键
     */
    private Integer taskId;
    /**
     * 用户任务id
     */
    private Integer userTaskId;
    /**
     * 任务类型 1-一次性任务 2-多次可完成任务
     */
    private Byte taskType;
    /**
     * 任务分组类型 1-下单 2-浏览 3-领取券 4-积分兑换
     */
    private Byte taskClass;
}
