package com.cheshun.vo.aicustomer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel("常见问题")
@AllArgsConstructor
@NoArgsConstructor
public class CommonProblemVO implements Serializable {

    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "该问题是否需要经纬度 1需要 0不需要")
    private String isReqEw;

}
