package com.cheshun.po.admin.game;

import com.cheshun.dto.game.match.MatchRelationBindDTO;
import com.cheshun.entity.game.match.MatchMaterial;

import java.io.Serializable;
import java.util.List;

public class MatchMaterialPO extends MatchMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<MatchRelationBindDTO> relationList;

    public List<MatchRelationBindDTO> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<MatchRelationBindDTO> relationList) {
        this.relationList = relationList;
    }
}