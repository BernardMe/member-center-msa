package com.cheshun.entity.coupon;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 券池领取记录表
 * @TableName plat_coupon_pool_record
 */
@TableName(value ="plat_coupon_pool_record")
@Data
public class PlatCouponPoolRecord {
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
     * 
     */
    @TableField(value = "pool_no")
    private String poolNo;

    /**
     * 优惠券编码
     */
    @TableField(value = "coupon_no")
    private String couponNo;

    /**
     * 创建时间
     */
    @TableField(value = "create_date")
    private LocalDateTime createDate;

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
        PlatCouponPoolRecord other = (PlatCouponPoolRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberCardNo() == null ? other.getMemberCardNo() == null : this.getMemberCardNo().equals(other.getMemberCardNo()))
            && (this.getPoolNo() == null ? other.getPoolNo() == null : this.getPoolNo().equals(other.getPoolNo()))
            && (this.getCouponNo() == null ? other.getCouponNo() == null : this.getCouponNo().equals(other.getCouponNo()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberCardNo() == null) ? 0 : getMemberCardNo().hashCode());
        result = prime * result + ((getPoolNo() == null) ? 0 : getPoolNo().hashCode());
        result = prime * result + ((getCouponNo() == null) ? 0 : getCouponNo().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
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
        sb.append(", poolNo=").append(poolNo);
        sb.append(", couponNo=").append(couponNo);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }
}