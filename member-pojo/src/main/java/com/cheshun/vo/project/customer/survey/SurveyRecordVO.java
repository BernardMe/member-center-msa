package com.cheshun.vo.project.customer.survey;

import java.io.Serializable;
import java.util.List;

public class SurveyRecordVO implements Serializable {

    /**
     * 会员卡号
     */
    private String memberCardNo;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 收件人地址
     */
    private String address;
    /**
     * 问卷id
     */
    private Integer paperId;
    /**
     * 回答列表
     */
    private List<SurveyAnswerVO> answerList;

    private static final long serialVersionUID = 1L;

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public List<SurveyAnswerVO> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<SurveyAnswerVO> answerList) {
        this.answerList = answerList;
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