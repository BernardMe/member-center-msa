package com.cheshun.entity.store;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class MZBSiteInfoExcel implements Serializable {

    @ExcelProperty(value = "大区")
    private String cMdfq;

    @ExcelProperty(value = "地区")
    private String cMdfq1;

    @ExcelProperty(value = "门诊部编号")
    private String subbh;

    @ExcelProperty(value = "门诊部名称")
    private String subname;

    @ExcelProperty(value = "经度")
    private String longitude;

    @ExcelProperty(value = "纬度")
    private String latitude;

    @ExcelProperty(value = "门诊部地址")
    private String mzbAdress;

    @ExcelProperty(value = "门诊部固定电话")
    private String mzbPhone;

    @ExcelProperty(value = "馆长")
    private String curator;

    @ExcelProperty(value = "馆长电话")
    private String curatorPhone;


}
