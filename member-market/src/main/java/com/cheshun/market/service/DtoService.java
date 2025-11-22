package com.cheshun.market.service;

import com.cheshun.market.domain.entity.*;
import com.cheshun.market.vo.dto.*;
import com.zzjdyf.market.domain.entity.*;
import com.zzjdyf.market.vo.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author 阿隆
 * Created on 2021/7/8 20:07.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DtoService {
    DtoService INSTANCE = Mappers.getMapper(DtoService.class);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgent}
     * @return {@link AgentAdminDto}
     */
    AgentAdminDto toDto(ClsMarketEtcAgent entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgent}
     * @return {@link AgentAdminDto}
     */
    AgentAppDto toAppDto(ClsMarketEtcAgent entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcGoods}
     * @return {@link GoodsAdminDto}
     */
    GoodsAdminDto toDto(ClsMarketEtcGoods entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcGoods}
     * @return {@link GoodsAdminDto}
     */
    GoodsAppDto toAppDto(ClsMarketEtcGoods entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgentOrder}
     * @return {@link OrderAppDto}
     */

    OrderAppDto toDto(ClsMarketEtcAgentOrder entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcGoodsSku}
     * @return {@link GoodsSkuAdminDto}
     */
    GoodsSkuAdminDto toDto(ClsMarketEtcGoodsSku entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgentOrderGoods}
     * @return {@link OrderGoodsAdminDto}
     */
    OrderGoodsAdminDto toDto(ClsMarketEtcAgentOrderGoods entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgentOrderGoods}
     * @return {@link OrderGoodsAppDto}
     */
    OrderGoodsAppDto toAppDto(ClsMarketEtcAgentOrderGoods entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgentGoods}
     * @return {@link AgentOrderAdminDto}
     */
    AgentOrderAdminDto toDto(ClsMarketEtcAgentGoods entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgentBankCard}
     * @return {@link AgentBankCardDto}
     */
    AgentBankCardDto toAppDto(ClsMarketEtcAgentBankCard entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgentWithdrawOrder}
     * @return {@link PromoteAppDto}
     */
    OrderAppDto toAppDto(ClsMarketEtcAgentOrder entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgentWithdrawOrder}
     * @return {@link OrderAppDto}
     */
    OrderAppDto toAppDto(ClsMarketEtcAgentWithdrawOrder entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcAgentWithdrawOrder}
     * @return {@link PromoteAppDto}
     */
    PromoteAppDto toAppDto(ClsMarketEtcAgentPromoteHistory entity);

    /**
     * entity to dto
     *
     * @param entity {@link ClsMarketEtcRole}
     * @return {@link RoleAdminDto}
     */
    RoleAdminDto toDto(ClsMarketEtcRole entity);


}
