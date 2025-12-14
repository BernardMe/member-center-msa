package com.cheshun.po.admin.plantation.survey;


import com.cheshun.entity.plantation.survey.PlantationSurPaperDTO;

import java.io.Serializable;
import java.util.List;

public class PlantationSurPaperPO extends PlantationSurPaperDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<PlantationSurQuestionPO> questionList;

    public List<PlantationSurQuestionPO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<PlantationSurQuestionPO> questionList) {
        this.questionList = questionList;
    }
}