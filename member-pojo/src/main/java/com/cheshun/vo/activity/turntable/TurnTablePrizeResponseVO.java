package com.cheshun.vo.activity.turntable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnTablePrizeResponseVO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 奖品类型
     */
    private String type;
    /**
     * 奖品编号
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
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
