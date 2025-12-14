package com.cheshun.po.project.customer.survey;

import com.cheshun.entity.project.customer.survey.SurveyOptionDTO;
import com.cheshun.entity.project.customer.survey.SurveyQuestionDTO;

import java.io.Serializable;
import java.util.List;

public class SurveyQuestionPO extends SurveyQuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SurveyOptionDTO> optionList;

    public List<SurveyOptionDTO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SurveyOptionDTO> optionList) {
        this.optionList = optionList;
    }
}