package com.cheshun.vo.store;


import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@ApiModel("门店信息展示")
public class StoreInfoVO {
    private Double lat;

    private Double lng;

    private String storeCode;

    private String storeName;

    private Double Distance;

    private String address;

    private String phoneNumber;

    private String summerOpenDate;

    private String summerCloseDate;

    private String winterOpenDate;

    private String winterCloseDate;

    private String status;
}
