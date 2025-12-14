package com.cheshun.po.project.erp.healthdetect;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class NcdDataDetail implements Serializable {

    /**
     * 检测项目分类
     */
    private String rmProjectCategory;
    /**
     * 测量值
     */
    private String rmMeasuredValue;
    /**
     * 是否异常
     */
    private String rmIsTypeAbnormal;

}
