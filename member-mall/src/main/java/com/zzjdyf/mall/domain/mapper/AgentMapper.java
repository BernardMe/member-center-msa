package com.zzjdyf.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.mall.domain.entity.enums.AgentRole;
import com.zzjdyf.mall.vo.dto.BonusDashboardAdminVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author wangzhuo
 * Created on 20210719
 */
@Mapper
@Repository
public interface AgentMapper extends BaseMapper<ClsMarketEtcAgent> {
    /**
     * 查询分润及提现金额统计
     *
     * @param role 一级代理商角色
     * @return 统计信息 {@link BonusDashboardAdminVo}
     */
    @Select("<script>select " +
            "sum(total_active_bonus + total_first_consume_bonus) as totalBonus, " +
            "sum(total_withdraw_amount) as totalWithdrawAmount, " +
            "0 as totalBalance " +
            "from cls_market_etc_agent where 1=1 " +
            "<when test=\"role !=null  \"> and role = #{role} </when>" +
            "<when test=\"staffId !=null \"> and staff_id =#{staffId} </when> " +
            "<when test=\"agentId !=null \"> and id =#{agentId} </when></script>")
    BonusDashboardAdminVo queryBonusStatistics(@Param("role") AgentRole role, @Param("staffId") Long staffId
            , @Param("agentId") Long agentId);

    /**
     * 增加累计激活奖励金额
     *
     * @param id          id
     * @param activeBonus 激活奖励增量
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_agent set " +
            "total_active_bonus = total_active_bonus + #{activeBonus}, " +
            "update_time = NOW() " +
            "where id = #{id}")
    int increaseActiveBonus(Long id, BigDecimal activeBonus);

    /**
     * 增加累计首次消费奖励金额
     *
     * @param id                id
     * @param firstConsumeBonus 首次消费奖励增量
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_agent set " +
            "total_first_consume_bonus = total_first_consume_bonus + #{firstConsumeBonus}, " +
            "update_time = NOW() " +
            "where id = #{id}")
    int increaseFirstConsumeBonus(Long id, BigDecimal firstConsumeBonus);

    /**
     * 增加累计提现金额
     *
     * @param id             id
     * @param withdrawAmount 提现金额增量
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_agent set " +
            "total_withdraw_amount = total_withdraw_amount + #{withdrawAmount}, " +
            "update_time = NOW() " +
            "where id = #{id} " +
            "and total_withdraw_amount + #{withdrawAmount} >= 0 " +
            "and total_active_bonus + total_first_consume_bonus >= total_withdraw_amount + #{withdrawAmount}")
    int increaseWithdrawAmount(Long id, BigDecimal withdrawAmount);

    /**
     * 更新累计提现金额
     *
     * @param id         id
     * @param statusList 提现状态
     */
    @Update({"<script>",
            "update cls_market_etc_agent set update_time = NOW(), total_withdraw_amount = ",
            "(select ifnull(sum(broker_amount), 0) from cls_market_etc_agent_withdraw_order where agent_id = #{id} and status in ",
            "<foreach collection=\"statusList\" item=\"status\" index=\"index\" open=\"(\" separator=\",\" close=\")\">",
            "#{status}",
            "</foreach>",
            ") where id = #{id}",
            "</script>"})
    void updateWithdrawAmount(@Param("id") Long id, @Param("statusList") Collection<String> statusList);


    /**
     * 批量更新代理商的提现状态
     * @param ids
     * @param enableWithdraw
     */
    @Update("<script>update cls_market_etc_agent set update_time = NOW(), enable_withdraw = #{enableWithdraw}, remarks =#{remarks} where " +
            "id in <foreach collection=\"ids\" item=\"id\" index=\"index\" open=\"(\" separator=\",\" close=\")\">#{id}</foreach></script>")
    void updateWithdrawStatus(@Param("ids") Collection<Long> ids, @Param("remarks") String remarks, @Param("enableWithdraw") Integer enableWithdraw);

}