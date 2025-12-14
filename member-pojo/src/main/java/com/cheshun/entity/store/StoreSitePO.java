package com.cheshun.entity.store;

import java.io.Serializable;

public class StoreSitePO implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 门店编号
     */
    private String subbh;
    /**
     * 手机定位经度
     */
    private String siteLong;
    /**
     * 手机定位纬度
     */
    private String siteLat;
    /**
     * 门店地址
     */
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSubbh() {
        return subbh;
    }

    public void setSubbh(String subbh) {
        this.subbh = subbh;
    }

    public String getSiteLong() {
        return siteLong;
    }

    public void setSiteLong(String siteLong) {
        this.siteLong = siteLong;
    }

    public String getSiteLat() {
        return siteLat;
    }

    public void setSiteLat(String siteLat) {
        this.siteLat = siteLat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
