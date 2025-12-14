package com.cheshun.entity.plantation;

import java.io.Serializable;

public class PlantationConfigDataDTO implements Serializable {
    private Integer dataId;

    private Integer configId;

    private String dataCode;

    private String dataName;

    private String dataValue;

    private Integer dateSort;

    private static final long serialVersionUID = 1L;

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode == null ? null : dataCode.trim();
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }

    public Integer getDateSort() {
        return dateSort;
    }

    public void setDateSort(Integer dateSort) {
        this.dateSort = dateSort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dataId=").append(dataId);
        sb.append(", configId=").append(configId);
        sb.append(", dataCode=").append(dataCode);
        sb.append(", dataName=").append(dataName);
        sb.append(", dataValue=").append(dataValue);
        sb.append(", dateSort=").append(dateSort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}