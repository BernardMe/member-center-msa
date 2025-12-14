package com.cheshun.entity.store;

import lombok.Data;

import java.io.Serializable;

@Data
public class MZBSiteInfo implements Serializable {

    private String cMdfq;

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
