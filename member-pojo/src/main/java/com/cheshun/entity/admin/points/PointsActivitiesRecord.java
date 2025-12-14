package com.cheshun.entity.admin.points;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 积分兑换记录表
 *
 * @TableName points_activities_record
 */
@TableName(value = "points_activities_record")
@Data
public class PointsActivitiesRecord {
    /**
     * 记录ID
     */
    @ApiModelProperty("记录ID")
    @TableId(type = IdType.AUTO)
    private Integer recordId;

    /**
     * 会员卡号
     */
    @ApiModelProperty("会员卡号")
    private String memberCardNo;

    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    private Integer activitiesId;

    /**
     * 兑换商品ID
     */
    @ApiModelProperty("兑换商品ID")
    private Integer goodsId;

    /**
     * 兑换数量
     */
    @ApiModelProperty("兑换数量")
    private Integer exchangeNum;

    /**
     * 兑换门店ID
     */
    @ApiModelProperty("兑换门店ID")
    private String storeId;

    /**
     * 兑换时间
     */
    @ApiModelProperty("兑换时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime exchangeTime;

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
        PointsActivitiesRecord other = (PointsActivitiesRecord) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
                && (this.getMemberCardNo() == null ? other.getMemberCardNo() == null : this.getMemberCardNo().equals(other.getMemberCardNo()))
                && (this.getActivitiesId() == null ? other.getActivitiesId() == null : this.getActivitiesId().equals(other.getActivitiesId()))
                && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
                && (this.getExchangeNum() == null ? other.getExchangeNum() == null : this.getExchangeNum().equals(other.getExchangeNum()))
                && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
                && (this.getExchangeTime() == null ? other.getExchangeTime() == null : this.getExchangeTime().equals(other.getExchangeTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getMemberCardNo() == null) ? 0 : getMemberCardNo().hashCode());
        result = prime * result + ((getActivitiesId() == null) ? 0 : getActivitiesId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getExchangeNum() == null) ? 0 : getExchangeNum().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getExchangeTime() == null) ? 0 : getExchangeTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", activitiesId=").append(activitiesId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", exchangeNum=").append(exchangeNum);
        sb.append(", storeId=").append(storeId);
        sb.append(", exchangeTime=").append(exchangeTime);
        sb.append("]");
        return sb.toString();
    }
}