package com.cheshun.po.user.plantation.config;

import java.util.List;

/**
 * ConfigGlobal信息PO
 * @author wangzhuo
 * @date 20240920
 */
public class PlantationConfigGlobalPO {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 配置编码，不能重复，唯一确定一项配置
     */
    private String configCode;
    /**
     * 配置名称
     */
    private String configName;
    /**
     * 配置类型(1:全局配置, 2:业务配置, 3:界面配置)
     */
    private Byte configType;
    /**
     * 配置数据类别(1:常规键值数据, 2:对象数据)
     */
    private Byte configDataClass;
    /**
     * 配置值
     */
    private String configValue;

    private List<PlantationConfigDataPO> configDataList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Byte getConfigType() {
        return configType;
    }

    public void setConfigType(Byte configType) {
        this.configType = configType;
    }

    public Byte getConfigDataClass() {
        return configDataClass;
    }

    public void setConfigDataClass(Byte configDataClass) {
        this.configDataClass = configDataClass;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public List<PlantationConfigDataPO> getConfigDataList() {
        return configDataList;
    }

    public void setConfigDataList(List<PlantationConfigDataPO> configDataList) {
        this.configDataList = configDataList;
    }
}
