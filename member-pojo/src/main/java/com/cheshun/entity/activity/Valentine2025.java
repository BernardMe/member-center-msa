package com.cheshun.entity.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 七夕节活动
 * @TableName valentine_2025
 */
@TableName(value ="valentine_2025")
@Data
public class Valentine2025 {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员卡号
     */
    @TableField(value = "member_card_no")
    private String memberCardNo;

    /**
     * 剩余抽奖次数
     */
    @TableField(value = "number")
    private Integer number;

    /**
     * 头像
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 参加时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

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
        Valentine2025 other = (Valentine2025) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberCardNo() == null ? other.getMemberCardNo() == null : this.getMemberCardNo().equals(other.getMemberCardNo()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberCardNo() == null) ? 0 : getMemberCardNo().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", number=").append(number);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", name=").append(name);
        sb.append(", creatTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}