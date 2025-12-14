package com.cheshun.vo.user.game.match;

import com.cheshun.entity.game.match.MatchRelation;

import java.io.Serializable;
import java.util.List;

public class MatchAnswerVO implements Serializable {

    /**
     * 用户任务id
     */
    private Integer userTaskId;
    /**
     * 素材id
     */
    private Integer materialId;
    /**
     * 回答列表
     */
    private List<MatchRelation> relationList;

    private static final long serialVersionUID = 1L;

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }

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