package com.cheshun.entity.user.plantation;

/**
 * 重置用户任务信息DTO
 * @author wangzhuo
 * @date 20240920
 */
public class PlantationResetUserTaskDTO {

    /**
     * 用户任务id
     */
    private Integer userTaskId;
    /**
     * 任务id
     */
    private Integer taskId;
    /**
     * 是否已删除(1:是, 0:否)
     */
    private Byte isDelete;

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
