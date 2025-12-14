package com.cheshun.vo.admin.plantation;

/**
 * 助力浇水传参VO
 * @author wangzhuo
 * @date 20241228
 */
public class AssistWateringVO {

    private Integer userTaskId;

    private String invitationId;

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }
}
