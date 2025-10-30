package com.zzjdyf.market.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcMeunMo extends BaseEntity{

    /**
     * 菜单id
     */
    private Long meunId;

    /**
     * mo名称
     */
    private String moName;


    private String moValue;


}
