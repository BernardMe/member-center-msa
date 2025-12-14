package com.cheshun.entity.member.activity.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class FloorData implements Serializable {

    private String eventsId;

    private String floor;
}
