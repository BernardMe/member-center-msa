package com.cheshun.market.vo.admin.game.math;

import com.zzjdyf.entity.game.match.MatchRelation;

import java.io.Serializable;
import java.util.List;

public class MatchMaterialRelationVO implements Serializable {
    /**
     * 素材id
     */
    private Integer materialId;
    /**
     * 关系列表
     */
    private List<MatchRelation> relationList;


    private static final long serialVersionUID = 1L;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public List<MatchRelation> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<MatchRelation> relationList) {
        this.relationList = relationList;
    }
}