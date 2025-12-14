package com.cheshun.entity.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 控糖分享活动参与记录表
 *
 * @TableName diabetes_share_join_record
 */
@TableName(value = "diabetes_share_join_record")
@Data
public class DiabetesShareJoinRecord {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动ID
     */
    @TableField(value = "base_id")
    private Integer baseId;

    /**
     * 会员卡号
     */
    @TableField(value = "member_card_no")
    private String memberCardNo;

    /**
     * 会员名称
     */
    @TableField(value = "member_name")
    private String memberName;

    /**
     * 会员手机号
     */
    @TableField(value = "member_phone")
    private String memberPhone;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 糖龄
     */
    @TableField(value = "sugar_age")
    private Integer sugarAge;

    /**
     * 目前控糖方式
     */
    @TableField(value = "sugar_control")
    private String sugarControl;

    /**
     * 我的故事/经验
     */
    @TableField(value = "story_experience")
    private String storyExperience;

    /**
     * 我的故事/经验-图片
     */
    @TableField(value = "story_experience_img")
    private String storyExperienceImg;

    /**
     * 我的故事/经验-视频
     */
    @TableField(value = "story_experience_mp4")
    private String storyExperienceMp4;

    /**
     * 点赞
     */
    @TableField(value = "like_number")
    private Integer likeNumber;

    /**
     * 评论
     */
    @TableField(value = "comment")
    private Integer comment;

    /**
     * 转发
     */
    @TableField(value = "forward")
    private Integer forward;

    /**
     * 得票
     */
    @TableField(value = "votes")
    private Integer votes;

    /**
     * 审核状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 审核时间
     */
    @TableField(value = "commit_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime commitTime;

    /**
     * 是否获奖
     */
    @TableField(value = "award")
    private String award;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DiabetesShareJoinRecord other = (DiabetesShareJoinRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getBaseId() == null ? other.getBaseId() == null : this.getBaseId().equals(other.getBaseId()))
                && (this.getMemberCardNo() == null ? other.getMemberCardNo() == null : this.getMemberCardNo().equals(other.getMemberCardNo()))
                && (this.getMemberName() == null ? other.getMemberName() == null : this.getMemberName().equals(other.getMemberName()))
                && (this.getMemberPhone() == null ? other.getMemberPhone() == null : this.getMemberPhone().equals(other.getMemberPhone()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getSugarAge() == null ? other.getSugarAge() == null : this.getSugarAge().equals(other.getSugarAge()))
                && (this.getSugarControl() == null ? other.getSugarControl() == null : this.getSugarControl().equals(other.getSugarControl()))
                && (this.getStoryExperience() == null ? other.getStoryExperience() == null : this.getStoryExperience().equals(other.getStoryExperience()))
                && (this.getStoryExperienceImg() == null ? other.getStoryExperienceImg() == null : this.getStoryExperienceImg().equals(other.getStoryExperienceImg()))
                && (this.getStoryExperienceMp4() == null ? other.getStoryExperienceMp4() == null : this.getStoryExperienceMp4().equals(other.getStoryExperienceMp4()))
                && (this.getLikeNumber() == null ? other.getLikeNumber() == null : this.getLikeNumber().equals(other.getLikeNumber()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
                && (this.getForward() == null ? other.getForward() == null : this.getForward().equals(other.getForward()))
                && (this.getVotes() == null ? other.getVotes() == null : this.getVotes().equals(other.getVotes()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getAward() == null ? other.getAward() == null : this.getAward().equals(other.getAward()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBaseId() == null) ? 0 : getBaseId().hashCode());
        result = prime * result + ((getMemberCardNo() == null) ? 0 : getMemberCardNo().hashCode());
        result = prime * result + ((getMemberName() == null) ? 0 : getMemberName().hashCode());
        result = prime * result + ((getMemberPhone() == null) ? 0 : getMemberPhone().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSugarAge() == null) ? 0 : getSugarAge().hashCode());
        result = prime * result + ((getSugarControl() == null) ? 0 : getSugarControl().hashCode());
        result = prime * result + ((getStoryExperience() == null) ? 0 : getStoryExperience().hashCode());
        result = prime * result + ((getStoryExperienceImg() == null) ? 0 : getStoryExperienceImg().hashCode());
        result = prime * result + ((getStoryExperienceMp4() == null) ? 0 : getStoryExperienceMp4().hashCode());
        result = prime * result + ((getLikeNumber() == null) ? 0 : getLikeNumber().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getForward() == null) ? 0 : getForward().hashCode());
        result = prime * result + ((getVotes() == null) ? 0 : getVotes().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAward() == null) ? 0 : getAward().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", baseId=").append(baseId);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", memberName=").append(memberName);
        sb.append(", memberPhone=").append(memberPhone);
        sb.append(", title=").append(title);
        sb.append(", sugarAge=").append(sugarAge);
        sb.append(", sugarControl=").append(sugarControl);
        sb.append(", storyExperience=").append(storyExperience);
        sb.append(", storyExperienceImg=").append(storyExperienceImg);
        sb.append(", storyExperienceMp4=").append(storyExperienceMp4);
        sb.append(", likeNumber=").append(likeNumber);
        sb.append(", comment=").append(comment);
        sb.append(", forward=").append(forward);
        sb.append(", votes=").append(votes);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", award=").append(award);
        sb.append("]");
        return sb.toString();
    }
}