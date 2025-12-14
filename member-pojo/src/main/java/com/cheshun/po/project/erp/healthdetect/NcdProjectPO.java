package com.cheshun.po.project.erp.healthdetect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ERP-NcdProjectPO列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NcdProjectPO implements Serializable {

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
     * longnumber
     */
    private String longnumber;
    /**
     * level
     */
    private Byte level;
    /**
     * fullname
     */
    private String fullname;
    /**
     * isleaf
     */
    private Boolean isleaf;
    /**
     * name_cn
     */
    private String nameCn;
    /**
     * rm_sort
     */
    private Byte rmSort;

}