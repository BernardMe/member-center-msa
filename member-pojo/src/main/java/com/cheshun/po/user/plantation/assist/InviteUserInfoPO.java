package com.cheshun.po.user.plantation.assist;

/**
 * 邀请用户信息PO
 * @author wangzhuo
 * @date 20241231
 */
public class InviteUserInfoPO {

    private String headImg;

    private String nickName;

    private Integer stageId;

    private String stageName;

    private String stageImage;


    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageImage() {
        return stageImage;
    }

    public void setStageImage(String stageImage) {
        this.stageImage = stageImage;
    }
}
