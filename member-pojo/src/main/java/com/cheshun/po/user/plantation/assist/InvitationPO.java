package com.cheshun.po.user.plantation.assist;

/**
 * 邀请信息PO
 * @author wangzhuo
 * @date 20241231
 */
public class InvitationPO {


    private String invitationId;

    private Integer noticesUser;


    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public Integer getNoticesUser() {
        return noticesUser;
    }

    public void setNoticesUser(Integer noticesUser) {
        this.noticesUser = noticesUser;
    }
}
