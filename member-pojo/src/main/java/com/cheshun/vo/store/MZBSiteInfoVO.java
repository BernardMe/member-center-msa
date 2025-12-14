package com.cheshun.vo.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MZBSiteInfoVO implements Serializable {

    @JsonProperty("cMdfq")
    private String cMdfq;

    @JsonProperty("cMdfq1")
    private String cMdfq1;

    private String subbh;

    private String subname;

    private String longitude;

    private String latitude;

    private String createtime;

    private String modifytime;

    private String mzbAdress;

    private String mzbPhone;

    private String curator;

    private String curatorPhone;
}
