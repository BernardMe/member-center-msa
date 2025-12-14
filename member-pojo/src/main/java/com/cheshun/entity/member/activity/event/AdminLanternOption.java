package com.cheshun.entity.member.activity.event;


import lombok.Data;

import java.io.Serializable;

@Data
public class AdminLanternOption implements Serializable {



    private String id;

    private String topic;

    private Integer maxPoint;

    private Integer maxTime;

    private String createtime;

    private String modifytime;

    private String isEnable;

    private String imagesUrl;


    private String fnumber;



}
