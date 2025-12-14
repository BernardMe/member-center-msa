package com.cheshun.vo.project.customer.survey;

import lombok.Data;

import java.io.Serializable;

@Data
public class SurveyMiniQrCodeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 问卷id
     */
    private Integer paperId;
    /**
     * 指定的页面路径
     */
    private String pageUrl;

}