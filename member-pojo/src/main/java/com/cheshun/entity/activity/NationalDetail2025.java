package com.cheshun.entity.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 国庆节活动明细表
 * @TableName national_detail_2025
 */
@TableName(value ="national_detail_2025")
@Data
public class NationalDetail2025 {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员卡号
     */
    @TableField(value = "member_card_no")
    private String memberCardNo;

    /**
     * 助力人会员卡号
     */
    @TableField(value = "helper_card_no")
    private String helperCardNo;

    /**
     * 助力形式
     */
    @TableField(value = "hepler_type")
    private Integer heplerType;

    /**
     * 卡片类型
     */
    @TableField(value = "card_type")
    private Integer cardType;

    @TableField(value = "hepl_date")
    private LocalDate heplDate;

    /**
     * 助力时间
     */
    @TableField(value = "creat_time")
    private LocalDateTime creatTime;

}