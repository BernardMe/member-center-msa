package com.cheshun.entity.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 转盘门店限制列表
 *
 * @TableName plat_act_turntable_store_list
 */
@TableName(value = "plat_act_turntable_store_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurntableStoreList {
    /**
     * 转盘ID
     */
    @TableField(value = "turntable_id")
    private Integer turntableId;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "sta_time")
    private Date staTime;

    /**
     * 门店列表
     */
    @TableField(value = "store_list")
    private String storeList;
}