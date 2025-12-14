package com.cheshun.vo.admin.brochures;

import lombok.Data;

import java.io.Serializable;

@Data
public class EquipVideoMiniQrCodeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * goodsCode
     */
    private String goodsCode;
    /**
     * 指定的页面路径
     */
    private String pageUrl;

}