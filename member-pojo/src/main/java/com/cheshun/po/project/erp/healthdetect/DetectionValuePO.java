package com.cheshun.po.project.erp.healthdetect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ERP-测量值PO列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetectionValuePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String number;
    /**
     * 患者会员卡号
     */
    private String ynMemberCardNo;
    /**
     * 患者名称
     */
    private String ynMemberName;
    /**
     * 患者性别
     */
    private Byte ynGender;
    /**
     * 患者年龄
     */
    private Byte ynAge;
    /**
     * 是否正常(0正常，1异常)
     */
    private Byte ynRmIsAbnormal;
    /**
     * 测量项目编码
     */
    private String projectNumber;
    /**
     * 时间节点编码(测量项目为血糖时传)
     * 注：血糖编码为G000002
     */
    private String nodeNumber;
    /**
     * 测量时间
     */
    private String YnRmFilingTimeStr;
    /**
     * NCD数据信息
     */
    private DetectionNcdDataInfo ncdDataInfo;
    /**
     * 测量门店编码
     */
    private String YnRmStoreCode;
    /**
     * 测量门店名称
     */
    private String YnRmStoreName;
    /**
     * 审核状态
     */
    private String status;
    /**
     * 数据状态
     */
    private String enable;


}