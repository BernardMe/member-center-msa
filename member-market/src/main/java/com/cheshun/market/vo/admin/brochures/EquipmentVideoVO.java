package com.cheshun.market.vo.admin.brochures;

import lombok.Data;

@Data
public class EquipmentVideoVO {

    private Integer id;
    /**
     * 器械id
     */
    private Integer equipmentId;
    /**
     * 货号
     */
    private String goodsCode;
    /**
     * 品名
     */
    private String equipmentName;
    /**
     * OSS对象名
     */
    private String ossObjectName;
    /**
     * 缩略图url
     */
    private String thumbnailImage;
    /**
     * 视频ossUrl
     */
    private String videoUrl;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 目录编码
     */
    private String categoryCode;
    /**
     * 目录名称
     */
    private String categoryName;
    /**
     * 点击次数
     */
    private Integer clickNum;
    /**
     * 状态(0禁用, 1正常)
     */
    private Byte status;

    @Override
    public String toString() {
        return "EquipmentVideoVO{" +
                "id=" + id +
                ", equipmentId=" + equipmentId +
                ", goodsCode='" + goodsCode + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", ossObjectName='" + ossObjectName + '\'' +
                ", thumbnailImage='" + thumbnailImage + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", clickNum=" + clickNum +
                ", status=" + status +
                '}';
    }
}
