package com.zzjdyf.auth.feign.goods.entity;

import lombok.*;

/**
 * Created by xueqing wang on 2021/4/21 17:35
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleLicenseSecondDriving  extends VehicleLicense{
    /**
     * 检验记录test_record
     */
    private String testRecord;

    /**
     * 核定载人数capacity
     */
    private String capacity;

    /**
     * 核定载质量approved_load
     */
    private String approvedLoad;

    /**
     * 备注notes
     */
    private String notes;

    /**
     * 总质量total_mass
     */
    private String totalMass;

    /**
     * 号牌号码plateno
     */
    private String plateno;

    /**
     * 档案编号document_no
     */
    private String documentNo;

    /**
     * 准牵引总质量traction_mass
     */
    private String tractionMass;

    /**
     * 装备质量mass
     */
    private String mass;

    /**
     * 外廓尺寸size
     */
    private String size;




}
