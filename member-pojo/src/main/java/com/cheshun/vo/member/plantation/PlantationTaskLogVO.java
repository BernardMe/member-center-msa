package com.cheshun.vo.member.plantation;

import lombok.Data;

import java.io.Serializable;

/**
 * 任务明细参数VO
 * @Author wangzhuo
 * @Date 20240828
 **/
@Data
public class PlantationTaskLogVO implements Serializable {

    /**
     * 任务主键
     */
    private Integer taskId;
    /**
     * 用户任务id
     */
    private Integer userTaskId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 浏览时间戳
     */
    private String pageBrwoseId;

}


