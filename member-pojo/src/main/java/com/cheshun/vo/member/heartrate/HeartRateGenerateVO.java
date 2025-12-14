package com.cheshun.vo.member.heartrate;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "异步调用生成心电PDF接口参数")
public class HeartRateGenerateVO implements Serializable {

    /**
     * 会员卡号
     */
    private String memberCardNo;
    /**
     * 报告id
     */
    private String zxdReportId;

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public String getZxdReportId() {
        return zxdReportId;
    }

    public void setZxdReportId(String zxdReportId) {
        this.zxdReportId = zxdReportId;
    }

    @Override
    public String toString() {
        return "HeartRateGenerateVO{" +
                "memberCardNo='" + memberCardNo + '\'' +
                ", zxdReportId='" + zxdReportId + '\'' +
                '}';
    }
}
