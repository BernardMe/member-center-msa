package com.cheshun.entity.user.plantation;

import java.io.Serializable;

/**
 * 用户任务信息明细DTO
 * @author wangzhuo
 * @date 20240826
 */
public class PlantationUserTaskLogDTO implements Serializable {

    /**
     * user_task_id
     */
    private Integer userTaskId;

    /**
     * 完成的次数
     */
    private Byte completeNum;

    public Byte getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Byte completeNum) {
        this.completeNum = completeNum;
    }

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }
}
