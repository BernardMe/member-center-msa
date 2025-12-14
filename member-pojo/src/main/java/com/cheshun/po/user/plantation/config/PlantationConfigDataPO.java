package com.cheshun.po.user.plantation.config;

/**
 * ConfigData信息PO
 * @author wangzhuo
 * @date 20240920
 */
public class PlantationConfigDataPO {

    /**
     * 主键
     */
    private Integer dataId;
    /**
     * 数据编码
     */
    private String dataCode;
    /**
     * 数据名称
     */
    private String dataName;
    /**
     * 数据排序
     */
    private Integer dataSort;
    /**
     * 数据值
     */
    private String dataValue;

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Integer getDataSort() {
        return dataSort;
    }

    public void setDataSort(Integer dataSort) {
        this.dataSort = dataSort;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
}
