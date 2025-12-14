package com.cheshun.entity.plantation;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: new_shop
 * @description: 百草园中药材类型
 * @author: clf
 * @create: 2024-07-25 15:46
 **/
@Data
public class PlantationHerbDTO {
    /**
     * 中药材id
     */
    private Integer herbId;

    /**
     * 中药材名称
     */
    private String herbName;

    /**
     * 中药材列表图片URL
     */
    private String herbImgUrl;

    /**
     * 中药材详情图片URL
     */
    private String herbImgDetailUrl;
    /**
     * 中药材详情
     */
    private String herbDetail;

    /**
     * 阶段数量
     */
    private Byte stageNum;

    /**
     * 启用状态(1:已启用, 0:未启用)
     */
    private Byte status;

    /**
     * 是否已删除(1:是, 0:否)
     */
    private String isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 中药材植株种植顺序
     */
    private Integer herbSort;
}
