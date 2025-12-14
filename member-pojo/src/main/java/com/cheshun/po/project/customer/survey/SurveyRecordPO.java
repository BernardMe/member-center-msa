package com.cheshun.po.project.customer.survey;

import com.cheshun.entity.project.customer.survey.SurveyRecordDTO;

import java.io.Serializable;

public class SurveyRecordPO extends SurveyRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String questionText;

    private String createTimeStr;

    private String updateTimeStr;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    @Override
    public String toString() {
        return "SurveyRecordPO{" +
                "questionText='" + questionText + '\'' +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                '}';
    }
}