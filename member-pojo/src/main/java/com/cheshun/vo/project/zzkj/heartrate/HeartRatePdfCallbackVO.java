package com.cheshun.vo.project.zzkj.heartrate;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "心电PDF报告回调接口参数")
public class HeartRatePdfCallbackVO implements Serializable {

    /**
     * 报告 id
     */
    private String reportId;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 报告文件路径
     */
    private String pdf;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        return "HeartRatePdfCallbackVO{" +
                "reportId='" + reportId + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", pdf='" + pdf + '\'' +
                '}';
    }
}
