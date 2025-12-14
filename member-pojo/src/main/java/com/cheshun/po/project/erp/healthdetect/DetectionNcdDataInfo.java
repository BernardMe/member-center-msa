package com.cheshun.po.project.erp.healthdetect;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * NCD检测数据信息
 */
@Data
@ApiModel
public class DetectionNcdDataInfo implements Serializable {

    /**
     * 测量项目
     */
    private String rmMeasureProject;
    /**
     * 时间节点
     */
    private String rmTimeNode;
    /**
     * 测量数据列表
     */
    private List<NcdDataDetail> ncdDataDetailList;

}
