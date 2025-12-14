package com.cheshun.vo.activity.turntable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminNewPrizeRequestVO {
    /**
     * 转盘ID
     */
    private Integer turntableId;
    /**
     * 名称
     */
    private String name;
    /**
     * 奖品类型（优惠券 虚拟商品）
     */
    private String type;
    /**
     * 奖品编号（虚拟商品无编号，优惠券为优惠券编码，便于后续发放）
     */
    private String code;
    /**
     * 中奖概率
     */
    private Integer probability;
    /**
     * 奖品图片
     */
    private String pictureUrl;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 优先级
     */
    private Integer sort;
    /**
     * 跳转链接
     */
    private String jumpUrl;
}
