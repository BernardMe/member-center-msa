package com.cheshun.vo.admin.plantation.survey;

import java.io.Serializable;

public class PlantationSurAnswerVO implements Serializable {
    /**
     * 题目id
     */
    private Integer questionId;
    /**
     * 题目类型
     */
    private Byte questionType;
    /**
     * 回答
     */
    private String answer;

    private static final long serialVersionUID = 1L;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Byte getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Byte questionType) {
        this.questionType = questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}