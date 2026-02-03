package com.cheshun.tcc.common.enums;

/**
 * TCC 事务状态枚举
 */
public enum TccStatus {
    /**
     * 尝试阶段
     */
    TRYING,

    /**
     * 确认阶段
     */
    CONFIRMING,

    /**
     * 取消阶段
     */
    CANCELING,

    /**
     * 已确认
     */
    CONFIRMED,

    /**
     * 已取消
     */
    CANCELED
}