package com.cheshun.entity.member.activity.event;

import lombok.Data;

import java.io.Serializable;
@Data
public class LanternShareData implements Serializable {


    private String shareMember;

    private String receiveMember;

    private String createtime;
}
