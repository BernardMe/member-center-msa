package com.cheshun.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String openid;

    private String unionid;

    private String token;

    private String memberCardNo;

    private String memberName;

    private String phoneNumber;

    private String headImg;

    private String gender;

    private String birthday;

    private String refreshToken;
}
