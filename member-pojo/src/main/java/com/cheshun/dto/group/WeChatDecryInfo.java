package com.cheshun.dto.group;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeChatDecryInfo implements Serializable {
    private String encryptedData;
    private String iv;
}
