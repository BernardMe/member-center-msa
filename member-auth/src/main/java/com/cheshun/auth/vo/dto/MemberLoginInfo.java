package com.cheshun.auth.vo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberLoginInfo implements Serializable {

    private String memberCardNo;

    private String openid;

    private String unionid;

    private String memberName;

    private String refreshToken;

    private String sessionKey;

    private String phoneNumber;

    private String createtime;

    private String modifytime;

    private String headImg;

    private String gender;

    private String birthday;

}
