package com.cheshun.vo.member.healthdetect;

import lombok.Data;

import java.util.List;

@Data
public class HealthDetectValueVO {

    private String memberNumber;
    private String memberCardNo;
    private String cardTypeNo;
    private String familyId;
    private String shopNo;
    private String sex;
    private String memberName;
    private String measureTime;
    private String memberBirthday;
    private String clerkNumber;
    private String entId;
    private String projectNumber;
    private String nodeNumber;

    private List<HealthEntityVO> addHealthEntityVOList;
}
