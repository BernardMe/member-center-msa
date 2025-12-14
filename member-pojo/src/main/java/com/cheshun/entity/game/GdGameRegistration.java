package com.cheshun.entity.game;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 掼蛋游戏报名表
 *
 * @TableName gd_game_registration
 */
@TableName(value = "gd_game_registration")
@Data
@ToString
public class GdGameRegistration {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 接送点（0-自驾）
     */
    @TableField(value = "pickup_point")
    private Integer pickupPoint;

    /**
     * 是否自带搭档（1-有搭档、2-无搭档）
     */
    @TableField(value = "has_partner")
    private Integer hasPartner;

    /**
     * 搭档姓名
     */
    @TableField(value = "partner_name")
    private String partnerName;

    /**
     * 搭档年龄
     */
    @TableField(value = "partner_age")
    private Integer partnerAge;

    /**
     * 搭档电话
     */
    @TableField(value = "partner_phone")
    private String partnerPhone;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime updateTime;
}