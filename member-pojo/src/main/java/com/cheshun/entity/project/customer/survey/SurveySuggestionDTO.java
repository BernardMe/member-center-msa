package com.cheshun.entity.project.customer.survey;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @TableName c_wzsurvey_suggestion
 */
@TableName(value = "c_wzsurvey_suggestion")
@Data
public class SurveySuggestionDTO implements Serializable {
    /**
     * 建议id
     */
    @TableId(type = IdType.AUTO)
    private Integer suggestionId;

    /**
     * 记录id
     */
    private Integer recordId;

    /**
     * 回复员工工号
     */
    private String replyEmpId;

    /**
     * 回复员工姓名
     */
    private String replyEmpName;

    /**
     * 回复状态（0/null：未回复，1：已回复）
     */
    private String replyStatus;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复时间
     */
    private LocalDateTime replyCreateDate;

    /**
     * 回复修改时间
     */
    private LocalDateTime replyUpdateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        SurveySuggestionDTO other = (SurveySuggestionDTO) that;
        return (this.getSuggestionId() == null ? other.getSuggestionId() == null : this.getSuggestionId().equals(other.getSuggestionId()))
                && (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
                && (this.getReplyEmpId() == null ? other.getReplyEmpId() == null : this.getReplyEmpId().equals(other.getReplyEmpId()))
                && (this.getReplyEmpName() == null ? other.getReplyEmpName() == null : this.getReplyEmpName().equals(other.getReplyEmpName()))
                && (this.getReplyStatus() == null ? other.getReplyStatus() == null : this.getReplyStatus().equals(other.getReplyStatus()))
                && (this.getReplyContent() == null ? other.getReplyContent() == null : this.getReplyContent().equals(other.getReplyContent()))
                && (this.getReplyCreateDate() == null ? other.getReplyCreateDate() == null : this.getReplyCreateDate().equals(other.getReplyCreateDate()))
                && (this.getReplyUpdateDate() == null ? other.getReplyUpdateDate() == null : this.getReplyUpdateDate().equals(other.getReplyUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSuggestionId() == null) ? 0 : getSuggestionId().hashCode());
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getReplyEmpId() == null) ? 0 : getReplyEmpId().hashCode());
        result = prime * result + ((getReplyEmpName() == null) ? 0 : getReplyEmpName().hashCode());
        result = prime * result + ((getReplyStatus() == null) ? 0 : getReplyStatus().hashCode());
        result = prime * result + ((getReplyContent() == null) ? 0 : getReplyContent().hashCode());
        result = prime * result + ((getReplyCreateDate() == null) ? 0 : getReplyCreateDate().hashCode());
        result = prime * result + ((getReplyUpdateDate() == null) ? 0 : getReplyUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", suggestionId=").append(suggestionId);
        sb.append(", recordId=").append(recordId);
        sb.append(", replyEmpId=").append(replyEmpId);
        sb.append(", replyEmpName=").append(replyEmpName);
        sb.append(", replyStatus=").append(replyStatus);
        sb.append(", replyContent=").append(replyContent);
        sb.append(", replyCreateDate=").append(replyCreateDate);
        sb.append(", replyUpdateDate=").append(replyUpdateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}