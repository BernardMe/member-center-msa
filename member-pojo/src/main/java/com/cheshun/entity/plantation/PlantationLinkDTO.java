package com.cheshun.entity.plantation;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: new_shop
 * @description:
 * @author: clf
 * @create: 2024-08-09 11:38
 **/
@Data
public class PlantationLinkDTO {

    /**
     * 任务链接ID
     */
    private Integer linkId;

    /**
     * 任务ID
     */
    private Integer taskId;

    /**
     * 链接名称
     */
    private String linkName;

    /**
     * 链接类型
     */
    private Short linkType;

    /**
     * 是否已删除(1:是, 0:否)
     */
    private Short isDelete;

    /**
     * 参数信息
     */
    private String param;

    /**
     * 路径
     */
    private String path;

    /**
     * 首页模块主键
     */
    private Long mainModuleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
