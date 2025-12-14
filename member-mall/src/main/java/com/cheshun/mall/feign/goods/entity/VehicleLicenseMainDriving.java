package com.cheshun.mall.feign.goods.entity;


import lombok.*;


/**
 * Created by xueqing wang on 2021/4/21 17:35
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleLicenseMainDriving extends VehicleLicense {
    /**
     * 发证日期issue_date
     */
    private String issueDate;

    /**
     * 车辆识别代码vin
     */
    private String vin;

    /**
     * 号牌号码plateno
     */
    private String plateno;

    /**
     * 注册日期register_date
     */
    private String registerDate;

    /**
     * 使用性质use_character
     */
    private String useCharacter;

    /**
     * 住址address
     */
    private String address;

    /**
     * 所有人owner
     */
    private String owner;

    /**
     * 品牌型号model
     */
    private String model;

    /**
     * 车辆类型vehicle_type
     */
    private String vehicleType;

    /**
     * 发动机号码engine_no
     */
    private String engineNo;

}
