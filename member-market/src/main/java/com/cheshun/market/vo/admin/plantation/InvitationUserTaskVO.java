package com.cheshun.market.vo.admin.plantation;

/**
 * 邀请关联用户任务传参VO
 * @author wangzhuo
 * @date 20241228
 */
public class InvitationUserTaskVO {

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
