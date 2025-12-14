package com.cheshun.entity.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 门店资质图片表
 *
 * @TableName mall_store_aptitude_image
 */
@TableName(value = "mall_store_aptitude_image")
@Data
public class MallStoreAptitudeImage {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 门店编号
     */
    @TableField(value = "store_code")
    private String storeCode;

    /**
     * 门店名称
     */
    @TableField(value = "store_name")
    private String storeName;

    /**
     * 图片地址
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 类型编号
     */
    @TableField(value = "type_code")
    private String typeCode;

    /**
     * 类型描述
     */
    @TableField(value = "type_description")
    private String typeDescription;

    /**
     * 排序序号
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 状态编号 0:已过期 1:未上传 2:未审核 3:已通过 4:已驳回 5:临期五天内
     */
    @TableField(value = "status_code")
    private Integer statusCode;

    /**
     * 是否有过期时间标记
     */
    @TableField(value = "expire_tag")
    private Integer expireTag;

    /**
     * 过期时间
     */
    @TableField(value = "expire_at")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expireAt;

    /**
     * 创建时间
     */
    @TableField(value = "create_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createAt;

    /**
     * 修改时间
     */
    @TableField(value = "update_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateAt;

    /**
     * 是否审核通过标记
     */
    @TableField(value = "audit_tag")
    private Integer auditTag;

    /**
     * 审核描述
     */
    @TableField(value = "audit_description")
    private String auditDescription;

    /**
     * 审核时间
     */
    @TableField(value = "audit_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime auditAt;

    /**
     * 删除标记
     */
    @TableField(value = "delete_tag")
    private Integer deleteTag;

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
        MallStoreAptitudeImage other = (MallStoreAptitudeImage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getStoreCode() == null ? other.getStoreCode() == null : this.getStoreCode().equals(other.getStoreCode()))
                && (this.getStoreName() == null ? other.getStoreName() == null : this.getStoreName().equals(other.getStoreName()))
                && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
                && (this.getTypeCode() == null ? other.getTypeCode() == null : this.getTypeCode().equals(other.getTypeCode()))
                && (this.getTypeDescription() == null ? other.getTypeDescription() == null : this.getTypeDescription().equals(other.getTypeDescription()))
                && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
                && (this.getStatusCode() == null ? other.getStatusCode() == null : this.getStatusCode().equals(other.getStatusCode()))
                && (this.getExpireTag() == null ? other.getExpireTag() == null : this.getExpireTag().equals(other.getExpireTag()))
                && (this.getExpireAt() == null ? other.getExpireAt() == null : this.getExpireAt().equals(other.getExpireAt()))
                && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
                && (this.getUpdateAt() == null ? other.getUpdateAt() == null : this.getUpdateAt().equals(other.getUpdateAt()))
                && (this.getAuditTag() == null ? other.getAuditTag() == null : this.getAuditTag().equals(other.getAuditTag()))
                && (this.getAuditDescription() == null ? other.getAuditDescription() == null : this.getAuditDescription().equals(other.getAuditDescription()))
                && (this.getAuditAt() == null ? other.getAuditAt() == null : this.getAuditAt().equals(other.getAuditAt()))
                && (this.getDeleteTag() == null ? other.getDeleteTag() == null : this.getDeleteTag().equals(other.getDeleteTag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStoreCode() == null) ? 0 : getStoreCode().hashCode());
        result = prime * result + ((getStoreName() == null) ? 0 : getStoreName().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getTypeCode() == null) ? 0 : getTypeCode().hashCode());
        result = prime * result + ((getTypeDescription() == null) ? 0 : getTypeDescription().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getStatusCode() == null) ? 0 : getStatusCode().hashCode());
        result = prime * result + ((getExpireTag() == null) ? 0 : getExpireTag().hashCode());
        result = prime * result + ((getExpireAt() == null) ? 0 : getExpireAt().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getUpdateAt() == null) ? 0 : getUpdateAt().hashCode());
        result = prime * result + ((getAuditTag() == null) ? 0 : getAuditTag().hashCode());
        result = prime * result + ((getAuditDescription() == null) ? 0 : getAuditDescription().hashCode());
        result = prime * result + ((getAuditAt() == null) ? 0 : getAuditAt().hashCode());
        result = prime * result + ((getDeleteTag() == null) ? 0 : getDeleteTag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", storeCode=").append(storeCode);
        sb.append(", storeName=").append(storeName);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", typeDescription=").append(typeDescription);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", statusCode=").append(statusCode);
        sb.append(", expireTag=").append(expireTag);
        sb.append(", expireAt=").append(expireAt);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", auditTag=").append(auditTag);
        sb.append(", auditDescription=").append(auditDescription);
        sb.append(", auditAt=").append(auditAt);
        sb.append(", deleteTag=").append(deleteTag);
        sb.append("]");
        return sb.toString();
    }
}