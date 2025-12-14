package com.cheshun.entity.project.customer.survey;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data // Lombok 注解，自动生成getter/setter
public class SurveyPaperTimeDTO {
    @ApiModelProperty(value = "问卷ID", required = true)
    @NotBlank // 验证注解
    private String paperId;

    @ApiModelProperty("问卷名称")
    private String paperName;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // Jackson时间格式化
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;
}
