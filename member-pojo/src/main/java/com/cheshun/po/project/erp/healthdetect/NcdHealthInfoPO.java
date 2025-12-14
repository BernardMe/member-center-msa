package com.cheshun.po.project.erp.healthdetect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ERP-NcdHealthInfoPO列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NcdHealthInfoPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 主键number
     */
    private String number;
    /**
     * 名称
     */
    private String name;
    /**
     * masterid
     */
    private String masterid;
    /**
     * rmUnit
     */
    private String rmUnit;
    /**
     * rmDesc
     */
    private String rmDesc;
    /**
     * rmDescTag
     */
    private String rmDescTag;
    /**
     * helpCode
     */
    private String helpCode;
    /**
     * rmAbbr
     */
    private String rmAbbr;

}