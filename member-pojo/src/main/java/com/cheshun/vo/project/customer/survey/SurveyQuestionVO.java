package com.cheshun.vo.project.customer.survey;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 保存问卷问题RedisVO类
 */
@Data
public class SurveyQuestionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * paperId
     */
    private Integer paperId;
    /**
     * 图片base64串
     */
    private JSONObject[] questionArr;

}