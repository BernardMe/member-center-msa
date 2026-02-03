package com.cheshun.tcc.common.context;

import lombok.Data;

/**
 * TCC 事务上下文
 */
@Data
public class TccContext {

    /**
     * 全局事务ID
     */
    private String transactionId;

    /**
     * 分支事务ID
     */
    private String branchId;

    /**
     * 事务状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Long createTime;

    private static ThreadLocal<TccContext> contextHolder = new ThreadLocal<>();

    public static TccContext getCurrentContext() {
        return contextHolder.get();
    }

    public static void setCurrentContext(TccContext context) {
        contextHolder.set(context);
    }

    public static void removeContext() {
        contextHolder.remove();
    }
}