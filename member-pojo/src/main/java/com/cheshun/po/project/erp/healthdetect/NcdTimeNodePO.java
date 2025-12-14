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
public class NcdTimeNodePO implements Serializable {

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
    private String ctrlstrategy;
    /**
     * rmDesc
     */
    private String sourcedata;
    /**
     * srcindex
     */
    private Integer srcindex;
    /**
     * 时间段最大值
     */
    private String fractionMax;
    /**
     * 时间段最小值
     */
    private String fractionMin;

}