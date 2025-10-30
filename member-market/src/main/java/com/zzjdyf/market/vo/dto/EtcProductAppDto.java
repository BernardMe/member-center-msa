package com.zzjdyf.market.vo.dto;

import com.zzjdyf.market.domain.entity.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@EqualsAndHashCode()
@Data
@ToString(callSuper = true)
@ApiModel("我的成员明细")
public class EtcProductAppDto {

    /**
     * 代理产品id
     */
    @ApiModelProperty("代理产品id")
    private Long id;
    /**
     * ETC卡号
     */
    @ApiModelProperty("ETC卡号")
    private String cardSn;
    /**
     * 卡状态
     */
    @ApiModelProperty("卡状态")
    private ProductStatus cardStatus;
    /**
     * 卡状态tab
     */
    @ApiModelProperty("卡状态tab")
    private String cardStatusTag;
    /**
     * OBU标签号
     */
    @ApiModelProperty("OBU标签号")
    private String deviceSn;
    /**
     * 设备状态
     */
    @ApiModelProperty("设备状态")
    private ProductStatus deviceStatus;
    /**
     * 设备状态tab
     */
    @ApiModelProperty("设备状态tab")
    private String deviceStatusTag;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;

}
