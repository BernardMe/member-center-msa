package com.cheshun.vo.store;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "门店位置信息参数")
public class StoreSiteVO implements Serializable {

    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 门店位置记录idList
     */
    private List<Integer> idList;
    /**
     * 手机定位经度
     */
    private String locLong;
    /**
     * 手机定位纬度
     */
    private String locLat;
    /**
     * 距离量
     */
    private Double distance;

    /**
     * 门店编号
     */
    private String storeCode;

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getLocLong() {
        return locLong;
    }

    public void setLocLong(String locLong) {
        this.locLong = locLong;
    }

    public String getLocLat() {
        return locLat;
    }

    public void setLocLat(String locLat) {
        this.locLat = locLat;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
