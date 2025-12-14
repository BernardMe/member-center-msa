package com.cheshun.entity.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName movie_store_info
 */
@TableName(value = "movie_store_info")
@Data
public class MovieStoreInfo {
    /**
     * 电影院地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 电影院名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * 大区
     */
    @TableField(value = "region")
    private String region;

    /**
     * 地市
     */
    @TableField(value = "area")
    private String area;

    /**
     * 地市经理
     */
    @TableField(value = "manager")
    private String manager;

    /**
     * 地区
     */
    @TableField(value = "area2")
    private String area2;

    /**
     * 区域经理
     */
    @TableField(value = "manager2")
    private String manager2;

    /**
     *
     */
    @TableField(value = "store_no")
    private String storeNo;

    /**
     * 门店名称
     */
    @TableField(value = "store_name")
    private String storeName;

    /**
     * 员工数量
     */
    @TableField(value = "employee_num")
    private String employeeNum;

    /**
     * 可邀约会员数
     */
    @TableField(value = "member_num")
    private String memberNum;

    /**
     * 电影票分配张数
     */
    @TableField(value = "movie_num")
    private String movieNum;

    /**
     * 人均需邀约会员数
     */
    @TableField(value = "avg_num")
    private String avgNum;

    @TableField(value = "batch")
    private String batch;

    @TableField(value = "end_time")
    private String endTime;
}