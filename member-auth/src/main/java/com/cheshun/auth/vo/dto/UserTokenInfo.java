package com.cheshun.auth.vo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserTokenInfo implements Serializable {

    private String accessToken;

    private Long expireTimestamp;
}
