package com.cheshun.po.project.erp.healthdetect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ERP-厂牌返回信息PO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceBrandPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键number
     */
    private String number;
    /**
     * 器械分类目录编码
     */
    private String ynDCategoryCode;
    /**
     * 器械分类目录名称
     */
    private String ynDCategoryName;
    /**
     * 器械厂牌编码
     */
    private String ynBaseBrandCode;
    /**
     * 器械厂牌名称
     */
    private String ynBaseBrandName;
}