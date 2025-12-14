package com.cheshun.entity.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 控糖分享活动一键三连表
 * @TableName diabetes_share_like_record
 */
@TableName(value ="diabetes_share_like_record")
@Data
public class DiabetesShareLikeRecord {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 记录ID
     */
    @TableField(value = "record_id")
    private Integer recordId;

    /**
     * 一键三连人工号
     */
    @TableField(value = "likes_member_card_no")
    private String likesMemberCardNo;

    /**
     * 喜欢类型
     */
    @TableField(value = "likes_type")
    private String likesType;

    /**
     * 评论
     */
    @TableField(value = "comment")
    private String comment;

    /**
     * 一键三连时间
     */
    @TableField(value = "likes_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime likesTime;

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
        DiabetesShareLikeRecord other = (DiabetesShareLikeRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getLikesMemberCardNo() == null ? other.getLikesMemberCardNo() == null : this.getLikesMemberCardNo().equals(other.getLikesMemberCardNo()))
            && (this.getLikesType() == null ? other.getLikesType() == null : this.getLikesType().equals(other.getLikesType()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getLikesTime() == null ? other.getLikesTime() == null : this.getLikesTime().equals(other.getLikesTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getLikesMemberCardNo() == null) ? 0 : getLikesMemberCardNo().hashCode());
        result = prime * result + ((getLikesType() == null) ? 0 : getLikesType().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getLikesTime() == null) ? 0 : getLikesTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", recordId=").append(recordId);
        sb.append(", likesMemberCardNo=").append(likesMemberCardNo);
        sb.append(", likesType=").append(likesType);
        sb.append(", comment=").append(comment);
        sb.append(", likesTime=").append(likesTime);
        sb.append("]");
        return sb.toString();
    }
}