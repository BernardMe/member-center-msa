package com.cheshun.po.project.customer.survey;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

public class ExcelSurveyQuestionPO {

    @ExcelIgnore
    private Integer id;
    /**
     * 货号
     */
    @ExcelProperty(value = "题目主干")
    @ColumnWidth(255)
    private String questionText;
    /**
     * 题目类型（1单选 2多选 3问答题）
     */
    @ExcelProperty(value = "题目类型（1单选 2多选 3问答题）")
    @ColumnWidth(3)
    private Byte questionType;
    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    @ColumnWidth(10)
    private Integer sort;
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
    private Byte source;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Byte getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Byte questionType) {
        this.questionType = questionType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }
}