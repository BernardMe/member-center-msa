package com.cheshun.po.user.plantation.plant;

import com.cheshun.entity.user.plantation.PlantationUserTaskViewDTO;

import java.io.Serializable;
import java.util.List;

/**
 * 用户任务中心视图MVO
 * @author wangzhuo
 * @date 20240826
 */
public class PlantationTaskViewMVO implements Serializable {

    private List<PlantationUserTaskViewDTO> userTaskInfoList;

    private String signReminder;

    public PlantationTaskViewMVO() {
    }

    public List<PlantationUserTaskViewDTO> getUserTaskInfoList() {
        return userTaskInfoList;
    }

    public void setUserTaskInfoList(List<PlantationUserTaskViewDTO> userTaskInfoList) {
        this.userTaskInfoList = userTaskInfoList;
    }

    public String getSignReminder() {
        return this.signReminder;
    }

    public void setSignReminder(String signReminder) {
        this.signReminder = signReminder;
    }

}
