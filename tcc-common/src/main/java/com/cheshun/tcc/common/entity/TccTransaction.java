package com.cheshun.tcc.common.entity;

import com.cheshun.tcc.common.enums.TccStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * TCC 事务记录
 */
@Data
public class TccTransaction {

    /**
     * 全局事务ID
     */
    private String transactionId;

    /**
     * 分支事务ID
     */
    private String branchId;

    /**
     * 资源ID（服务名称）
     */
    private String resourceId;

    /**
     * 事务状态
     */
    private TccStatus status;

    /**
     * 业务参数（JSON格式）
     */
    private String businessParams;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 重试次数
     */
    private Integer retryCount;
}