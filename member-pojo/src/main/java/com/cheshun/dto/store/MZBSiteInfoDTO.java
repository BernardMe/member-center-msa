package com.cheshun.dto.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "添加门诊部信息参数")
public class MZBSiteInfoDTO implements Serializable {

    @ApiModelProperty(value = "大区", example = "郑州东区", required = false)
    @JsonProperty("cMdfq")
    private String cMdfq;

    @ApiModelProperty(value = "地区", example = "郑东1区", required = false)
    @JsonProperty("cMdfq1")
    private String cMdfq1;

    @ApiModelProperty(value = "门诊部编号", example = "0371121001", required = true)
    private String subbh;

    @ApiModelProperty(value = "门诊部名称", example = "****中医门诊部", required = true)
    private String subname;

    @ApiModelProperty(value = "经度", example = "116.397477", required = true)
    private String longitude;

    @ApiModelProperty(value = "纬度", example = "39.908692", required = true)
    private String latitude;

    @ApiModelProperty(value = "门诊部地址", example = "郑州河医立交桥向东100米路北", required = true)
    private String mzbAdress;

    @ApiModelProperty(value = "门诊部固定电话", example = "0371-88888888", required = true)
    private String mzbPhone;

    @ApiModelProperty(value = "馆长", example = "张三", required = false)
    private String curator;


    @ApiModelProperty(value = "馆长电话", example = "13888888888", required = false)
    private String curatorPhone;

}
