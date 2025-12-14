package com.cheshun.po.project.customer.survey;

import com.cheshun.entity.project.customer.survey.SurveyPaperDTO;

import java.io.Serializable;
import java.util.List;

public class SurveyPaperPO extends SurveyPaperDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sourceStr;

    private String startTimeStr;

    private String endTimeStr;

    private String createTimeStr;

    private List<SurveyQuestionPO> questionList;

    public String getSourceStr() {
        return sourceStr;
    }

    public void setSourceStr(String sourceStr) {
        this.sourceStr = sourceStr;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public List<SurveyQuestionPO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<SurveyQuestionPO> questionList) {
        this.questionList = questionList;
    }
}