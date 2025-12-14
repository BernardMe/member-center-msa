package com.cheshun.entity.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipHasTurnTableTimesPO {
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 转盘ID
     */
    private Integer turnTable;
    /**
     * 当天次数上限
     */
    private Integer maxNum;
    /**
     * 限制值
     */
    private Integer limitValue;
    /**
     * x天抽y次 x
     */
    private Integer limitDays;
    /**
     * x天抽y次 y
     */
    private Integer limitTimes;
    /**
     * 指定门店编号列表（仅转盘类型为消费时可做限制）
     */
    private List<String> stroeCodeList;
    private LocalDateTime costSta;
    private LocalDateTime costEnd;
    private LocalDateTime actStaTime;
    private LocalDateTime actEndTime;
}
