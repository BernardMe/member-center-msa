package com.cheshun.dto.game.match;

import com.cheshun.entity.game.match.MatchRelation;
import java.io.Serializable;

public class MatchRelationBindDTO extends MatchRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String goodsName;

    private String featureText;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getFeatureText() {
        return featureText;
    }

    public void setFeatureText(String featureText) {
        this.featureText = featureText;
    }
}