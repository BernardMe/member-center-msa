package com.cheshun.market.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentOrderGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhuo
 * Created on 20210719
 */
@Mapper
@Repository
public interface AgentOrderGoodsMapper extends BaseMapper<ClsMarketEtcAgentOrderGoods> {
    /**
     * 分页查询订单商品
     *
     * @param page      分页参数
     * @param joinAgent 是否join代理商表
     * @param wrapper   查询条件
     * @return 订单商品列表
     */
    @Select("<script> select " +
            "og.* " +
            "from cls_market_etc_agent_order_goods as og " +
            "<if test='joinAgent'> left join cls_market_etc_agent as agent on agent.id = og.agent_id </if> " +
            "${ew.customSqlSegment} " +
            "</script>")
    IPage<ClsMarketEtcAgentOrderGoods> queryPage(IPage<ClsMarketEtcAgentOrderGoods> page,
                                                 @Param("joinAgent") boolean joinAgent,
                                                 @Param(Constants.WRAPPER) Wrapper<ClsMarketEtcAgentOrderGoods> wrapper);

    /**
     * 移动端_分页查询我的或下级成员的订单商品列表
     *
     * @param page      分页参数
     * @param wrapper   查询条件
     * @return 订单商品列表
     */
    @Select("<script> SELECT " +
            "t.* " +
            "FROM ( " +
            " select og.* " +
            " from cls_market_etc_agent_order_goods as og " +
            "${ew.customSqlSegment} " +
            ") t " +
            "</script>")
    IPage<ClsMarketEtcAgentOrderGoods> queryPage4Agent(IPage<ClsMarketEtcAgentOrderGoods> page,
                                                 @Param(Constants.WRAPPER) Wrapper<ClsMarketEtcAgentOrderGoods> wrapper);

    /**
     * 移动端_分页查询我的订单商品管理列表
     *
     * @param page      分页参数
     * @param wrapper   查询条件
     * @return 订单商品列表
     */
    @Select("<script> SELECT " +
            "a.* " +
            "FROM ( " +
            " select og.* " +
            " from cls_market_etc_agent_order_goods as og " +
            "${ew.customSqlSegment} " +
            ") a " +
            "LEFT JOIN (select og.agent_id, og.card_sn, og.device_sn " +
            " from cls_market_etc_agent_return_goods as og " +
            "${ew.customSqlSegment} and og.status='0' ) b  " +
            "<choose> " +
            "<when test=\"snType == 'card_sn'\"> ON a.card_sn = b.card_sn WHERE b.card_sn IS NULL </when> " +
            "<otherwise> ON a.device_sn = b.device_sn WHERE b.device_sn IS NULL  </otherwise> " +
            "</choose> " +
            "</script>")
    IPage<ClsMarketEtcAgentOrderGoods> queryPage4AgentMng(IPage<ClsMarketEtcAgentOrderGoods> page,
                                                       @Param(Constants.WRAPPER) Wrapper<ClsMarketEtcAgentOrderGoods> wrapper,
                                                       @Param("snType") String snType);
}