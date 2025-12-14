package com.cheshun.po.project.zzkj.heartrate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class HeartRateSubmitPO {
    //会员id
    private String memberId;
    //心电报告id
    private String zxdReportId;
    //原始心电数据OSS存储url
    private String oriDataOssUrl;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getZxdReportId() {
        return zxdReportId;
    }

    public void setZxdReportId(String zxdReportId) {
        this.zxdReportId = zxdReportId;
    }

    public String getOriDataOssUrl() {
        return oriDataOssUrl;
    }

    public void setOriDataOssUrl(String oriDataOssUrl) {
        this.oriDataOssUrl = oriDataOssUrl;
    }

    @Override
    public String toString() {
        return "HeartRateSubmitPO{" +
                "memberId='" + memberId + '\'' +
                ", zxdReportId='" + zxdReportId + '\'' +
                ", oriDataOssUrl='" + oriDataOssUrl + '\'' +
                '}';
    }
}
