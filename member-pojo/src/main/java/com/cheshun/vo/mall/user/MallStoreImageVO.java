package com.cheshun.vo.mall.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 门店端-当前门店资质图片vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MallStoreImageVO {
    /**
     * 主键id
     */
    private String id;

    /**
     * 门店编号
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 图片地址
     */
    private List<String> imageUrlList;

    /**
     * 类型编号
     */
    private String typeCode;

    /**
     * 类型描述
     */
    private String typeDescription;

    /**
     * 排序序号
     */
    private Integer orderNum;

    /**
     * 状态编号
     * 0:已过期 1:未上传 2:未审核 3:已通过 4:已驳回 5:临期五天内
     */
    private Integer statusCode;

    /**
     * 状态名称
     */
    private String statusName;

    /**
     * 是否有过期时间标记
     */
    private String expireTag;

    /**
     * 过期时间
     */
    private LocalDateTime expireDateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createDateTime;

    /**
     * 修改时间
     */
    private LocalDateTime changeDateTime;

    /**
     * 是否审核通过标记
     */
    private String auditTag;

    /**
     * 审核描述
     */
    private String auditDescription;

    /**
     * 审核时间
     */
    private LocalDateTime auditDateTime;

    /**
     * 是否必传
     */
    private Boolean isNecessary;

}
