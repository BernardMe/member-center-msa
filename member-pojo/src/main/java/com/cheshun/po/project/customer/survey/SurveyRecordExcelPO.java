package com.cheshun.po.project.customer.survey;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * 问卷-提交记录
 */
public class SurveyRecordExcelPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "会员卡号", index = 0)
    private String memberCardNo;
    @ExcelProperty(value = "问卷id", index = 1)
    private Integer paperId;
    @ExcelProperty(value = "会员姓名", index = 2)
    private String userName;
    @ExcelProperty(value = "提交时间Str", index = 3)
    private String createTimeStr;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}