package com.zzjdyf.mall.domain.entity;

import com.zzjdyf.mall.domain.entity.enums.PublishStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * ETC商品规格信息
 *
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcGoodsSku extends BaseEntity {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 押金(元)
     */
    private BigDecimal deposit;
    /**
     * 补货比例
     */
    private BigDecimal replRatio;
    /**
     * 补货时是否全补
     */
    private Boolean fullRepl;
    /**
     * 状态(未上架|已上架)
     */
    private PublishStatus status;
}