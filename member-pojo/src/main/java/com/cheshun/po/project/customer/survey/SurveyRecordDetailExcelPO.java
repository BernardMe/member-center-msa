package com.cheshun.po.project.customer.survey;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * 问卷-提交详情信息
 */
public class SurveyRecordDetailExcelPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "记录id", index = 0)
    private String recordId;
    @ExcelProperty(value = "会员卡号", index = 1)
    private String memberCardNo;
    @ExcelProperty(value = "试卷id", index = 2)
    private Integer paperId;
    @ExcelProperty(value = "题目排序", index = 3)
    private Integer sort;
    @ExcelProperty(value = "题目类型（1单选 2多选 3问答题）", index = 4)
    private Byte questionType;
    @ExcelProperty(value = "题干", index = 5)
    private String questionText;
    @ExcelProperty(value = "用户答案", index = 6)
    private String answer;
    @ExcelProperty(value = "会员姓名", index = 7)
    private String userName;
    @ExcelProperty(value = "地址", index = 8)
    private String address;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Byte questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}