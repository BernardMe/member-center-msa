package com.cheshun.po.admin.plantation.survey;

import com.cheshun.entity.plantation.survey.PlantationSurRecordDTO;

import java.io.Serializable;

public class PlantationSurRecordPO extends PlantationSurRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String questionText;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}