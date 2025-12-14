package com.cheshun.vo.project.zzkj.heartrate;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "心电算法回调接口参数")
public class HeartRateCallbackVO implements Serializable {


    /**
     * 报告 id
     */
    private String reportId;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 状态码
     */
    private Integer code;
    /**
     *
     */
    private String sampr;
    /**
     *
     */
    private String filteredEcgFilePath;
    /**
     * 平均心率
     */
    private Integer aveHr;
    /**
     * 最快心率
     */
    private Integer maxHr;
    /**
     * 最慢心率
     */
    private Integer minHr;
    /**
     * 心率状况
     */
    private Integer heartPace;
    /**
     * 最快心率发生时间
     */
    private Integer maxHrTime;
    /**
     * 最慢心率发生时间
     */
    private Integer minHrTime;
    /**
     * 心搏总数
     */
    private Integer peakSum;
    /**
     * 心动过速总时间
     */
    private Long heartFastTime;
    /**
     * 心动过缓总时间
     */
    private Long heartSlowTime;
    /**
     *
     */
    private String rrs;
    /**
     *
     */
    private Integer hrvScore;
    /**
     *
     */
    private Integer hrvIndex;
    /**
     *
     */
    private Integer alcoholRiskIndex;
    /**
     *
     */
    private Integer alcoholRiskScore;
    /**
     *
     */
    private Integer stressIndex;
    /**
     *
     */
    private Integer stressScore;
    /**
     *
     */
    private Integer heartPressureIndex;
    /**
     *
     */
    private Integer heartPressureScore;
    /**
     * RR 间期标准差
     */
    private Float sdnn;
    /**
     * 连续差分的均方根
     */
    private Float rmssd;
    /**
     * 总功率/总能量
     */
    private Float tp;
    /**
     * 超低频功率
     */
    private Float vlf;
    /**
     * 低频功率
     */
    private Float lf;
    /**
     * 高频功率
     */
    private Float hf;
    /**
     * 归一化低频功率
     */
    private Float lfNorm;
    /**
     * 归一化高频功率
     */
    private Float hfNorm;
    /**
     * 低频与高频功率
     * 比值（LF/HF）
     */
    private Float lfhfRatio;
    /**
     * 心律失常标签集合
     */
    private List<Integer> classifyResult;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSampr() {
        return sampr;
    }

    public void setSampr(String sampr) {
        this.sampr = sampr;
    }

    public String getFilteredEcgFilePath() {
        return filteredEcgFilePath;
    }

    public void setFilteredEcgFilePath(String filteredEcgFilePath) {
        this.filteredEcgFilePath = filteredEcgFilePath;
    }

    public Integer getAveHr() {
        return aveHr;
    }

    public void setAveHr(Integer aveHr) {
        this.aveHr = aveHr;
    }

    public Integer getMaxHr() {
        return maxHr;
    }

    public void setMaxHr(Integer maxHr) {
        this.maxHr = maxHr;
    }

    public Integer getMinHr() {
        return minHr;
    }

    public void setMinHr(Integer minHr) {
        this.minHr = minHr;
    }

    public Integer getHeartPace() {
        return heartPace;
    }

    public void setHeartPace(Integer heartPace) {
        this.heartPace = heartPace;
    }

    public Integer getMaxHrTime() {
        return maxHrTime;
    }

    public void setMaxHrTime(Integer maxHrTime) {
        this.maxHrTime = maxHrTime;
    }

    public Integer getMinHrTime() {
        return minHrTime;
    }

    public void setMinHrTime(Integer minHrTime) {
        this.minHrTime = minHrTime;
    }

    public Integer getPeakSum() {
        return peakSum;
    }

    public void setPeakSum(Integer peakSum) {
        this.peakSum = peakSum;
    }

    public Long getHeartFastTime() {
        return heartFastTime;
    }

    public void setHeartFastTime(Long heartFastTime) {
        this.heartFastTime = heartFastTime;
    }

    public Long getHeartSlowTime() {
        return heartSlowTime;
    }

    public void setHeartSlowTime(Long heartSlowTime) {
        this.heartSlowTime = heartSlowTime;
    }

    public String getRrs() {
        return rrs;
    }

    public void setRrs(String rrs) {
        this.rrs = rrs;
    }

    public Integer getHrvScore() {
        return hrvScore;
    }

    public void setHrvScore(Integer hrvScore) {
        this.hrvScore = hrvScore;
    }

    public Integer getHrvIndex() {
        return hrvIndex;
    }

    public void setHrvIndex(Integer hrvIndex) {
        this.hrvIndex = hrvIndex;
    }

    public Integer getAlcoholRiskIndex() {
        return alcoholRiskIndex;
    }

    public void setAlcoholRiskIndex(Integer alcoholRiskIndex) {
        this.alcoholRiskIndex = alcoholRiskIndex;
    }

    public Integer getAlcoholRiskScore() {
        return alcoholRiskScore;
    }

    public void setAlcoholRiskScore(Integer alcoholRiskScore) {
        this.alcoholRiskScore = alcoholRiskScore;
    }

    public Integer getStressIndex() {
        return stressIndex;
    }

    public void setStressIndex(Integer stressIndex) {
        this.stressIndex = stressIndex;
    }

    public Integer getStressScore() {
        return stressScore;
    }

    public void setStressScore(Integer stressScore) {
        this.stressScore = stressScore;
    }

    public Integer getHeartPressureIndex() {
        return heartPressureIndex;
    }

    public void setHeartPressureIndex(Integer heartPressureIndex) {
        this.heartPressureIndex = heartPressureIndex;
    }

    public Integer getHeartPressureScore() {
        return heartPressureScore;
    }

    public void setHeartPressureScore(Integer heartPressureScore) {
        this.heartPressureScore = heartPressureScore;
    }

    public Float getSdnn() {
        return sdnn;
    }

    public void setSdnn(Float sdnn) {
        this.sdnn = sdnn;
    }

    public Float getRmssd() {
        return rmssd;
    }

    public void setRmssd(Float rmssd) {
        this.rmssd = rmssd;
    }

    public Float getTp() {
        return tp;
    }

    public void setTp(Float tp) {
        this.tp = tp;
    }

    public Float getVlf() {
        return vlf;
    }

    public void setVlf(Float vlf) {
        this.vlf = vlf;
    }

    public Float getLf() {
        return lf;
    }

    public void setLf(Float lf) {
        this.lf = lf;
    }

    public Float getHf() {
        return hf;
    }

    public void setHf(Float hf) {
        this.hf = hf;
    }

    public Float getLfNorm() {
        return lfNorm;
    }

    public void setLfNorm(Float lfNorm) {
        this.lfNorm = lfNorm;
    }

    public Float getHfNorm() {
        return hfNorm;
    }

    public void setHfNorm(Float hfNorm) {
        this.hfNorm = hfNorm;
    }

    public Float getLfhfRatio() {
        return lfhfRatio;
    }

    public void setLfhfRatio(Float lfhfRatio) {
        this.lfhfRatio = lfhfRatio;
    }

    public List<Integer> getClassifyResult() {
        return classifyResult;
    }

    public void setClassifyResult(List<Integer> classifyResult) {
        this.classifyResult = classifyResult;
    }

    @Override
    public String toString() {
        return "HeartRateCallbackVO{" +
                "reportId='" + reportId + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", sampr='" + sampr + '\'' +
                ", filteredEcgFilePath='" + filteredEcgFilePath + '\'' +
                ", aveHr=" + aveHr +
                ", maxHr=" + maxHr +
                ", minHr=" + minHr +
                ", heartPace=" + heartPace +
                ", maxHrTime=" + maxHrTime +
                ", minHrTime=" + minHrTime +
                ", peakSum=" + peakSum +
                ", heartFastTime=" + heartFastTime +
                ", heartSlowTime=" + heartSlowTime +
                ", rrs='" + rrs + '\'' +
                ", hrvScore=" + hrvScore +
                ", hrvIndex=" + hrvIndex +
                ", alcoholRiskIndex=" + alcoholRiskIndex +
                ", alcoholRiskScore=" + alcoholRiskScore +
                ", stressIndex=" + stressIndex +
                ", stressScore=" + stressScore +
                ", heartPressureIndex=" + heartPressureIndex +
                ", heartPressureScore=" + heartPressureScore +
                ", sdnn=" + sdnn +
                ", rmssd=" + rmssd +
                ", tp=" + tp +
                ", vlf=" + vlf +
                ", lf=" + lf +
                ", hf=" + hf +
                ", lfNorm=" + lfNorm +
                ", hfNorm=" + hfNorm +
                ", lfhfRatio=" + lfhfRatio +
                ", classifyResult=" + classifyResult +
                '}';
    }
}
