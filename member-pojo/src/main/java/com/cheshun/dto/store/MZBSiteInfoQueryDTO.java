package com.cheshun.dto.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "查询门诊部信息参数")
public class MZBSiteInfoQueryDTO implements Serializable {

    @ApiModelProperty(value = "页数", example = "1", required = true)
    private String page;

    @ApiModelProperty(value = "页数量", example = "10", required = true)
    private String pageSize;

    @ApiModelProperty(value = "大区", example = "郑州东区", name = "cMdfq", required = false)
    @JsonProperty("cMdfq")
    private String cMdfq;

    @ApiModelProperty(value = "地区", example = "郑东1区", name = "cMdfq1", required = false)
    @JsonProperty("cMdfq1")
    private String cMdfq1;


    @ApiModelProperty(value = "门诊部编号", example = "0371121001", required = false)
    private String subbh;

    @ApiModelProperty(value = "门诊部名称", example = "****中医门诊部", required = false)
    private String subname;


}
