package com.cheshun.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.mall.domain.entity.ClsMarketEtcAgentGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author wangzhuo
 * Created on 20211103
 */
@Mapper
@Repository
public interface AgentGoodsMapper extends BaseMapper<ClsMarketEtcAgentGoods> {

    /**
     * 更新代理商代理商品信息状态
     *
     * @param auditGoodsSkuId   待审核商品规格id
     * @param quantity 设备数量增量
     * @param paidDeposit       实缴纳押金
     * @param id             id
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_agent_goods set " +
            "goods_sku_id = #{auditGoodsSkuId}, " +
            "audit_goods_sku_id = null, " +
            "quantity = #{quantity}, " +
            "deposit = #{paidDeposit}, " +
            "update_time = NOW() " +
            "where id = #{id} ")
    int confirmGoodsSkuAndDeposit(@Param("auditGoodsSkuId") Long auditGoodsSkuId, @Param("quantity") Integer quantity,
                                  @Param("paidDeposit") BigDecimal paidDeposit, @Param("id") Long id);

    /**
     * 回退代理商代理商品信息状态
     *
     * @param id             id
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_agent_goods set " +
            "audit_goods_sku_id = null, " +
            "status = 2, " +
            "update_time = NOW() " +
            "where id = #{id} ")
    int revertGoodsSkuAndStatus(Long id);

    /**
     * 失效代理商代理商品信息状态
     *
     * @param isEnabled  是否有效(0:无效, 1:有效)
     * @param id             id
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_agent_goods set " +
            "is_enabled = #{isEnabled}, " +
            "update_time = NOW() " +
            "where id = #{id} ")
    int disEnableAgentGoods(Boolean isEnabled, Long id);

    /**
     * 更新代理商代理商品信息状态(需缴纳押金,实缴纳押金)
     *
     * @param auditGoodsSkuId  待审核商品规格id
     * @param quantity 设备数量增量
     * @param deposit         需缴纳押金
     * @param paidDeposit     实缴纳押金
     * @param id             id
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_agent_goods set " +
            "goods_sku_id = #{auditGoodsSkuId}, " +
            "audit_goods_sku_id = null, " +
            "quantity = #{quantity}, " +
            "deposit = #{deposit}, " +
            "paid_deposit = #{paidDeposit}, " +
            "update_time = NOW() " +
            "where id = #{id} ")
    int confirmGoodsSkuAndDeposit4Retrun(@Param("auditGoodsSkuId") Long auditGoodsSkuId,
                                         @Param("quantity") Integer quantity, @Param("deposit") BigDecimal deposit,
                                         @Param("paidDeposit") BigDecimal paidDeposit, @Param("id") Long id);
}