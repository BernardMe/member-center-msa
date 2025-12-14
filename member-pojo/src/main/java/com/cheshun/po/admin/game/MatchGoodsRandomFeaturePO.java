package com.cheshun.po.admin.game;

import java.io.Serializable;
import java.util.List;

public class MatchGoodsRandomFeaturePO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer materialId;

    private List<MatchEntryPO> goodsList;

    private List<MatchEntryPO> featureList;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public List<MatchEntryPO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<MatchEntryPO> goodsList) {
        this.goodsList = goodsList;
    }

    public List<MatchEntryPO> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<MatchEntryPO> featureList) {
        this.featureList = featureList;
    }
}