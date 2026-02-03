package com.cheshun.tcc.common.transaction;

/**
 * TCC 事务管理器接口
 */
public interface TccTransactionManager {

    /**
     * 开始全局事务
     * @return 事务ID
     */
    String beginTransaction();

    /**
     * 提交事务
     * @param transactionId 事务ID
     */
    void commit(String transactionId);

    /**
     * 回滚事务
     * @param transactionId 事务ID
     */
    void rollback(String transactionId);

    /**
     * 注册分支事务
     * @param transactionId 全局事务ID
     * @param branchId 分支事务ID
     * @param resourceId 资源ID
     */
    void registerBranch(String transactionId, String branchId, String resourceId);
}