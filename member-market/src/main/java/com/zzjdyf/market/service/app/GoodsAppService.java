package com.zzjdyf.market.service.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.domain.dao.AgentDao;
import com.zzjdyf.market.domain.dao.AgentGoodsDao;
import com.zzjdyf.market.domain.dao.GoodsDao;
import com.zzjdyf.market.domain.dao.GoodsSkuDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentGoods;
import com.zzjdyf.market.domain.entity.ClsMarketEtcGoods;
import com.zzjdyf.market.domain.entity.ClsMarketEtcGoodsSku;
import com.zzjdyf.market.domain.entity.enums.DepositStatus;
import com.zzjdyf.market.domain.entity.enums.PublishStatus;
import com.zzjdyf.market.service.DtoService;
import com.zzjdyf.market.vo.dto.GoodsAppDto;
import com.zzjdyf.market.vo.query.IdQuery;
import com.zzjdyf.market.vo.query.PageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wangzhuo
 * Created on 20210803
 */
@Slf4j
@Service
@AllArgsConstructor
public class GoodsAppService {
    private final AgentDao agentDao;
    private final AgentGoodsDao agentGoodsDao;
    private final GoodsDao goodsDao;
    private final GoodsSkuDao goodsSkuDao;

    /**
     * 分页查询商品列表
     *
     * @param id    代理商id
     * @param query 条件 {@link PageQuery}
     * @return 商品列表  {@link IPage< GoodsAppDto >}
     */
    public IPage<GoodsAppDto> query(Long id, PageQuery query) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        // 获取该代理商正要代理或正在代理的商品列表
        List<ClsMarketEtcAgentGoods> agentGoodsList = agentGoodsDao.findAllByAgent(agent.getId());
        Map<Long, List<ClsMarketEtcAgentGoods>> agentGoodsMap = agentGoodsList
                .stream()
                .collect(Collectors.groupingBy(ClsMarketEtcAgentGoods::getGoodsId));
        LambdaQueryWrapper<ClsMarketEtcGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcGoods::getStatus, PublishStatus.ADDED);
        queryWrapper.orderByDesc(ClsMarketEtcGoods::getCreateTime);
        IPage<ClsMarketEtcGoods> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = goodsDao.selectPage(page, queryWrapper);
        List<GoodsAppDto> list = page.getRecords().stream().map(t -> toDto(t, agentGoodsMap)).collect(Collectors.toList());
        return new Page<GoodsAppDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }

    /**
     * 根据id查询商品
     *
     * @param id    代理商id
     * @param query 条件 {@link IdQuery}
     * @return 商品  {@link GoodsAppDto}
     */
    public GoodsAppDto get(Long id, IdQuery query) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(query.getId()))
                .orElseThrow(() -> new RRException("商品不存在"));
        // 获取该代理商正要代理或正在代理的商品列表
        List<ClsMarketEtcAgentGoods> agentGoodsList = agentGoodsDao.findAllByAgent(agent.getId());
        Map<Long, List<ClsMarketEtcAgentGoods>> agentGoodsMap = agentGoodsList
                .stream()
                .collect(Collectors.groupingBy(ClsMarketEtcAgentGoods::getGoodsId));
        return toDetailAppDto(goods, agentGoodsMap);
    }

    private GoodsAppDto toDto(ClsMarketEtcGoods goods, Map<Long, List<ClsMarketEtcAgentGoods>> agentGoodsMap) {
        GoodsAppDto dto = DtoService.INSTANCE.toAppDto(goods);
        // 押金状态
        if (agentGoodsMap.containsKey(goods.getId())) {
            ClsMarketEtcAgentGoods agentGoods = agentGoodsMap.get(goods.getId()).get(0);
            if (agentGoods.getStatus() != DepositStatus.RETURN) {
                dto.setDepositStatus(agentGoods.getStatus());
            }
        }
        return dto;
    }

    private GoodsAppDto toDetailAppDto(ClsMarketEtcGoods goods, Map<Long, List<ClsMarketEtcAgentGoods>> agentGoodsMap) {
        GoodsAppDto dto = toDto(goods, agentGoodsMap);
        // 获取商品既有规格列表
        List<ClsMarketEtcGoodsSku> skuList = goodsSkuDao.findAllByGoods(goods.getId());
        dto.setSkuList(skuList.stream()
                .filter(t -> t.getStatus() == PublishStatus.ADDED)
                .map(DtoService.INSTANCE::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
