package com.cheshun.market.domain.entity.enums;

/**
 * 全局配置名称枚举
 *
 * @author 阿隆
 * Created on 2021/8/12 15:00.
 */
public enum GlobalSettingKey {
    /**
     * 代理商推介用户成功申办，并现场安装ETC套装。每激活一套，代理商即可获得的奖励
     */
    AGENT_OFFLINE_ACTIVE_BONUS,
    /**
     * 代理商推介用户成功申办，并现场安装ETC套装。每激活一套，代理商即可获得的额外奖励
     */
    AGENT_OFFLINE_EXTRA_ACTIVE_BONUS,
    /**
     * 代理商推介用户成功申办，并现场安装ETC套装。当月激活量超过该阈值后，每激活一套，即可获得额外的奖励
     */
    AGENT_OFFLINE_MORE_EXTRA_ACTIVE_BONUS_THRESHOLD,
    /**
     * 代理商推介用户成功申办，并现场安装ETC套装。当月激活量超过{@link GlobalSettingKey#AGENT_OFFLINE_MORE_EXTRA_ACTIVE_BONUS_THRESHOLD}后，每激活一套，即可获得的额外奖励
     */
    AGENT_OFFLINE_MORE_EXTRA_ACTIVE_BONUS,
    /**
     * 代理商推介用户成功申办，并现场安装ETC套装。用户首次消费后，代理商即可获得的奖励
     */
    AGENT_OFFLINE_FIRST_CONSUME_BONUS,
    /**
     * 代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装。每激活一套，代理商即可获得的奖励
     */
    AGENT_ONLINE_ACTIVE_BONUS,
    /**
     * 代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装。当月激活量超过该阈值后，每激活一套，即可获得额外的奖励
     */
    AGENT_ONLINE_MORE_EXTRA_ACTIVE_BONUS_THRESHOLD,
    /**
     * 代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装。当月激活量超过{@link GlobalSettingKey#AGENT_ONLINE_MORE_EXTRA_ACTIVE_BONUS_THRESHOLD}后，每激活一套，即可获得的额外奖励
     */
    AGENT_ONLINE_MORE_EXTRA_ACTIVE_BONUS,
    /**
     * 代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装。用户首次消费后，代理商即可获得的奖励
     */
    AGENT_ONLINE_FIRST_CONSUME_BONUS,
    /**
     * 员工直接推介用户成功申办并激活安装ETC套装。每激活一套，员工即可获得的奖励
     */
    STAFF_BONUS
}
