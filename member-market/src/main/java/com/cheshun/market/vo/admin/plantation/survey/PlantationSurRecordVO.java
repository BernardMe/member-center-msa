package com.cheshun.market.vo.admin.plantation.survey;

import java.io.Serializable;
import java.util.List;

public class PlantationSurRecordVO implements Serializable {

    /**
     * 问卷id
     */
    private Integer paperId;
    /**
     * 回答列表
     */
    private List<PlantationSurAnswerVO> answerList;

    private static final long serialVersionUID = 1L;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public List<PlantationSurAnswerVO> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<PlantationSurAnswerVO> answerList) {
        this.answerList = answerList;
    }

}