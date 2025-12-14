package com.cheshun.entity.game;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 轮椅申请表
 * @TableName wheelchair_application
 */
@TableName(value ="wheelchair_application")
@Data
public class WheelchairApplication {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 
     */
    @TableField(value = "member_card_no")
    private String memberCardNo;

    /**
     * 
     */
    @TableField(value = "reason")
    private String reason;

    /**
     * 
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 
     */
    @TableField(value = "relationship")
    private String relationship;

    /**
     * 
     */
    @TableField(value = "store_no")
    private String storeNo;

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
        WheelchairApplication other = (WheelchairApplication) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getMemberCardNo() == null ? other.getMemberCardNo() == null : this.getMemberCardNo().equals(other.getMemberCardNo()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getRelationship() == null ? other.getRelationship() == null : this.getRelationship().equals(other.getRelationship()))
            && (this.getStoreNo() == null ? other.getStoreNo() == null : this.getStoreNo().equals(other.getStoreNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getMemberCardNo() == null) ? 0 : getMemberCardNo().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getRelationship() == null) ? 0 : getRelationship().hashCode());
        result = prime * result + ((getStoreNo() == null) ? 0 : getStoreNo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", reason=").append(reason);
        sb.append(", userName=").append(userName);
        sb.append(", relationship=").append(relationship);
        sb.append(", storeNo=").append(storeNo);
        sb.append("]");
        return sb.toString();
    }
}