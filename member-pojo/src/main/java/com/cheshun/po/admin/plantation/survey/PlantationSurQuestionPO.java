package com.cheshun.po.admin.plantation.survey;


import com.cheshun.entity.plantation.survey.PlantationSurOptionDTO;
import com.cheshun.entity.plantation.survey.PlantationSurQuestionDTO;

import java.io.Serializable;
import java.util.List;

public class PlantationSurQuestionPO extends PlantationSurQuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<PlantationSurOptionDTO> optionList;

    public List<PlantationSurOptionDTO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<PlantationSurOptionDTO> optionList) {
        this.optionList = optionList;
    }
}