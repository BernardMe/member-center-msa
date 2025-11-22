package com.cheshun.market.vo.admin.plantation;

import com.zzjdyf.po.user.plantation.config.PlantationConfigDataPO;

import java.util.List;

/**
 * 运营端-配置管理传参VO
 * @author wangzhuo
 * @date 20240920
 */
public class PlantationConfigParamVO {

    private Integer id;
    /**
     * 配置数据类别(1:常规键值数据, 2:对象数据)
     */
    private Byte configDataClass;

    private String configValue;

    private List<PlantationConfigDataPO> configDataList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
