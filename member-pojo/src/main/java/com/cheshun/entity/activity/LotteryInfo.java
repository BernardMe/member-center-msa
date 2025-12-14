package com.cheshun.entity.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 抽奖信息表
 * @TableName lottery_info
 */
@TableName(value ="lottery_info")
@Data
public class LotteryInfo {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 性别，1男，2女，0未知
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 省份
     */
    @TableField(value = "province")
    private String province;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 国家
     */
    @TableField(value = "country")
    private String country;

    /**
     * 头像
     */
    @TableField(value = "headimgurl")
    private String headimgurl;

    /**
     * 
     */
    @TableField(value = "unionid")
    private String unionid;

    /**
     * 创建时间
     */
    @TableField(value = "create_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createAt;

    /**
     * 奖品类型
     */
    @TableField(value = "prize")
    private String prize;


    /**
     * 中奖时间
     */
    @TableField(value = "prize_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime prizeTime;

}