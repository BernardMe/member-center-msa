package com.cheshun.market.vo.commonquestion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "问题类型")
public class QuestionTypeVO implements Serializable {

    @ApiModelProperty(value = "问题类型id")
    private Integer typeId;

    @ApiModelProperty(value = "问题类型名称")
    private String typeName;

    @ApiModelProperty(value = "是否显示1 0:不显示")
    private Integer isDisplay;

    @ApiModelProperty(value = "权重 值越大越往前")
    private Integer weightValue;

    @ApiModelProperty(value = "创建时间")
    private String createtime;

    @ApiModelProperty(value = "修改时间")
    private String modifytime;

}
