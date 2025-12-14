package com.cheshun.vo.check;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "OCR识别图片关键字参数")
@Data
public class OCRDetectVO implements Serializable {

    /**
     * 图片URL
     */
    private String imageUrl;
    /**
     * 待验证关键字
     */
    private String keyWordText;

}
