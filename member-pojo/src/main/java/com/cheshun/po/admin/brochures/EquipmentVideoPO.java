package com.cheshun.po.admin.brochures;

import lombok.Data;

@Data
public class EquipmentVideoPO {

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
     * 子级目录名称
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
    /**
     * 创建时间
     */
    private String createTimeStr;
    /**
     * 修改时间
     */
    private String updateTimeStr;

}
