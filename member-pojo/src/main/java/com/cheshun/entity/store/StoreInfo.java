package com.cheshun.entity.store;

import lombok.Data;

@Data
public class StoreInfo {

    private Double lat;

    private Double lng;

    private String code;

    private String name;

    private String address;

    private String phoneNumber;

    private String summerOpenDate;

    private String summerCloseDate;

    private String winterOpenDate;

    private String winterCloseDate;

    private String status;

}
