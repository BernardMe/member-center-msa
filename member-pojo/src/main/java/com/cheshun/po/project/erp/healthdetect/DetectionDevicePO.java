package com.cheshun.po.project.erp.healthdetect;

import lombok.Data;

import java.io.Serializable;

/**
 * 查看器械列表返回PO
 */
@Data
public class DetectionDevicePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键number
     */
    private String number;
    /**
     * 器械目录编码
     */
    private String ynDCategoryCode;
    /**
     * 器械目录名称
     */
    private String ynDCategoryName;
    /**
     * 器械SN
     */
    private String ynDeviceSN;
    /**
     * 添加员工工号
     */
    private String ynEmpCode;
    /**
     * 添加时间
     */
    private String ynAddTime;
    /**
     * 更新员工工号
     */
    private String ynEmpCodeUpd;
    /**
     * 更新时间
     */
    private String ynUpdTime;
    /**
     * 门店编码
     */
    private String ynSubbh;
    /**
     * 厂牌编码
     */
    private String ynBaseBrandCode;
    /**
     * 厂牌名称
     */
    private String ynBaseBrandName;
    /**
     * 蓝牙macId
     */
    private String ynDMacId;
    /**
     * 蓝牙macAddr
     */
    private String ynDMacAddr;
    /**
     * 审核状态
     */
    private String status;
    /**
     * 数据状态
     */
    private String enable;

}
