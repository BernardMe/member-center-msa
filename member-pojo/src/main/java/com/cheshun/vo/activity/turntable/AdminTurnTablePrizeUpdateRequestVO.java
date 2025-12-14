package com.cheshun.vo.activity.turntable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTurnTablePrizeUpdateRequestVO {
    /**
     * 主键
     */
    private Integer id;
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
