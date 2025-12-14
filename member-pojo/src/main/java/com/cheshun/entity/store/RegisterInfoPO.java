package com.cheshun.entity.store;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterInfoPO {
    private String memberName;
    private String birthday;
    private String gender;
    private String storeCode;
}
