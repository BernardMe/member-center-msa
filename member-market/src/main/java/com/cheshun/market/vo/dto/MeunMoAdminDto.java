package com.cheshun.market.vo.dto;


import com.cheshun.market.domain.entity.ClsMarketEtcMeunMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @version 0.1.0
 */
@ApiModel("菜单和功能权限信息")
@Data
@ToString(callSuper = true)
public class MeunMoAdminDto implements Serializable {

    /**
     * 菜单id
     */
    private Long meunId;

    /**
     * 菜单名称
     */
    private String meunName;


    List<ClsMarketEtcMeunMo> clsMarketEtcMeunMos ;

}
