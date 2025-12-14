package com.cheshun.vo.admin.points;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cheshun.entity.admin.points.PointsActivities;
import com.cheshun.entity.admin.points.PointsGoodsActivities;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 积分活动表
 *
 * @TableName points_activities
 */

@Data
public class PointsActivitiesVO {
    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    @TableId(type = IdType.AUTO)
    private Integer activitiesId;

    /**
     * 活动名称
     */
    @ApiModelProperty("活动名称")
    private String activitiesName;

    /**
     * 活动状态（1：启用，0：禁用）
     */
    @ApiModelProperty("活动状态（1：启用，0：禁用）")
    private String activitiesStatus;

    /**
     * 活动介绍
     */
    @ApiModelProperty("活动介绍")
    private String activitiesContent;

    /**
     * 活动背景图片地址
     */
    @ApiModelProperty("活动背景图片地址")
    private String activitiesBackground;

    /**
     * 活动背景图片名称
     */
    @ApiModelProperty("活动背景图片名称")
    private String activitiesBackgroundName;

    /**
     * 活动开始时间
     */
    @ApiModelProperty("活动开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty("活动结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /**
     * 活动创建时间
     */
    @ApiModelProperty("活动创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime activitiesCreatTime;

    @ApiModelProperty("积分商品活动列表")
    private List<PointsGoodsActivities> pointsGoodsActivitiesList;

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
        PointsActivities other = (PointsActivities) that;
        return (this.getActivitiesId() == null ? other.getActivitiesId() == null : this.getActivitiesId().equals(other.getActivitiesId()))
                && (this.getActivitiesName() == null ? other.getActivitiesName() == null : this.getActivitiesName().equals(other.getActivitiesName()))
                && (this.getActivitiesStatus() == null ? other.getActivitiesStatus() == null : this.getActivitiesStatus().equals(other.getActivitiesStatus()))
                && (this.getActivitiesContent() == null ? other.getActivitiesContent() == null : this.getActivitiesContent().equals(other.getActivitiesContent()))
                && (this.getActivitiesBackground() == null ? other.getActivitiesBackground() == null : this.getActivitiesBackground().equals(other.getActivitiesBackground()))
                && (this.getActivitiesBackgroundName() == null ? other.getActivitiesBackgroundName() == null : this.getActivitiesBackgroundName().equals(other.getActivitiesBackgroundName()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
                && (this.getActivitiesCreatTime() == null ? other.getActivitiesCreatTime() == null : this.getActivitiesCreatTime().equals(other.getActivitiesCreatTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActivitiesId() == null) ? 0 : getActivitiesId().hashCode());
        result = prime * result + ((getActivitiesName() == null) ? 0 : getActivitiesName().hashCode());
        result = prime * result + ((getActivitiesStatus() == null) ? 0 : getActivitiesStatus().hashCode());
        result = prime * result + ((getActivitiesContent() == null) ? 0 : getActivitiesContent().hashCode());
        result = prime * result + ((getActivitiesBackground() == null) ? 0 : getActivitiesBackground().hashCode());
        result = prime * result + ((getActivitiesBackgroundName() == null) ? 0 : getActivitiesBackgroundName().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getActivitiesCreatTime() == null) ? 0 : getActivitiesCreatTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activitiesId=").append(activitiesId);
        sb.append(", activitiesName=").append(activitiesName);
        sb.append(", activitiesStatus=").append(activitiesStatus);
        sb.append(", activitiesContent=").append(activitiesContent);
        sb.append(", activitiesBackground=").append(activitiesBackground);
        sb.append(", activitiesBackgroundName=").append(activitiesBackgroundName);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", activitiesCreatTime=").append(activitiesCreatTime);
        sb.append("]");
        return sb.toString();
    }
}