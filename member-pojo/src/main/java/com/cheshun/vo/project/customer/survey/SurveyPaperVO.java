package com.cheshun.vo.project.customer.survey;

import java.io.Serializable;
import java.util.List;


public class SurveyPaperVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 问卷id
     */
    private Integer paperId;
    /**
     * 问卷名称
     */
    private String paperName;
    /**
     * 启用状态(1:已启用, 0:未启用)
     */
    private Byte status;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否发放优惠券(0否,1是)
     */
    private Byte isIssueCoupon;
    /**
     * 优惠券方案编码
     */
    private String couponNo;
    /**
     * 是否定向发送(0否,1是)
     */
    private Byte isDirected;
    /**
     * 是否导出地址(0否,1是)
     */
    private Byte isExportDely;
    /**
     * 前言Str
     */
    private String forewordText;
    /**
     * 后记Str
     */
    private String epilogueText;
    /**
     * 有效期开始时间Str
     */
    private String startTimeStr;
    /**
     * 有效期截至时间Str
     */
    private String endTimeStr;


    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getIsIssueCoupon() {
        return isIssueCoupon;
    }

    public void setIsIssueCoupon(Byte isIssueCoupon) {
        this.isIssueCoupon = isIssueCoupon;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public Byte getIsDirected() {
        return isDirected;
    }

    public void setIsDirected(Byte isDirected) {
        this.isDirected = isDirected;
    }

    public Byte getIsExportDely() {
        return isExportDely;
    }

    public void setIsExportDely(Byte isExportDely) {
        this.isExportDely = isExportDely;
    }

    public String getForewordText() {
        return forewordText;
    }

    public void setForewordText(String forewordText) {
        this.forewordText = forewordText;
    }

    public String getEpilogueText() {
        return epilogueText;
    }

    public void setEpilogueText(String epilogueText) {
        this.epilogueText = epilogueText;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}