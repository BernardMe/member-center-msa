package com.cheshun.vo.activity.turntable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTurnTableUpdateRequestVO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 海报图片
     */
    private String pictureUrl;
    /**
     * 奖品数量
     */
    private Integer goodsNum;
    /**
     * 次数上限
     */
    private Integer maxNum;
    /**
     * x天抽y次 x
     */
    private Integer limitDays;
    /**
     * x天抽y次 y
     */
    private Integer limitNum;
    /**
     * 手动开关
     */
    private String manualSwitch;
    /**
     * 是否限制门店
     */
    private String limStore;
    /**
     * 删除标记
     */
    private String deleteTag;
    /**
     * 周几
     */
    private List<Integer> weekDaysList;
    private String costSta;
    private String costEnd;
    private String actStaTime;
    private String actEndTime;
    private String timeType;
}
