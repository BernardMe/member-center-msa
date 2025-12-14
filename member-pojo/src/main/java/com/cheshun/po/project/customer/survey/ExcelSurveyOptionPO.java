package com.cheshun.po.project.customer.survey;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

public class ExcelSurveyOptionPO {

    @ExcelIgnore
    private Integer questionId;
    /**
     * 题目排序
     */
    @ExcelProperty(value = "题目排序")
    @ColumnWidth(10)
    private Integer sort;
    /**
     * 选项值
     */
    @ExcelProperty(value = "选项值")
    @ColumnWidth(10)
    private String optionValue;
    /**
     * 选项文本
     */
    @ExcelProperty(value = "选项文本")
    @ColumnWidth(255)
    private String optionText;
    /**
     * 启用状态(1:已启用, 0:未启用)
     */
    @ExcelProperty(value = "启用状态(1:已启用, 0:未启用)")
    @ColumnWidth(3)
    private Byte status;
    /**
     * 来源(1:1V1快链)
     */
    @ExcelProperty(value = "来源(1:1V1快链)")
    @ColumnWidth(100)
    private String source;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}