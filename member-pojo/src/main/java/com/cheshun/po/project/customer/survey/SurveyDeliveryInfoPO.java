package com.cheshun.po.project.customer.survey;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * 问卷-邮寄信息
 */
public class SurveyDeliveryInfoPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "会员手机号", index = 0)
    private String userPhone;
    @ExcelProperty(value = "会员姓名", index = 1)
    private String userName;
    @ExcelProperty(value = "地址", index = 2)
    private String address;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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