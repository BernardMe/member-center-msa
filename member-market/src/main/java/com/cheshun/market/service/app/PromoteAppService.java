package com.cheshun.market.service.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.dao.AgentPromoteHistoryDao;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.service.DtoService;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.vo.dto.PromoteAppDto;
import com.cheshun.market.vo.query.PromotePageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 阿隆
 * Created on 2021/7/29 9:07 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class PromoteAppService {
    private final AgentDao agentDao;
    private final AgentPromoteHistoryDao agentPromoteHistoryDao;

    /**
     * 分页查询代理商分润记录
     *
     * @param id    代理商id
     * @param query 条件 {@link PromotePageQuery}
     * @return 代理商分润记录  {@link IPage< PromoteAppDto >}
     */
    public IPage<PromoteAppDto> query(Long id, PromotePageQuery query) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        LambdaQueryWrapper<ClsMarketEtcAgentPromoteHistory> queryWrapper = new LambdaQueryWrapper<>();
        switch (agent.getRole()) {
            case AgentRole.STAFF:
                // 是员工
                if (query.getType() == PromotePageQuery.QueryType.CONSUMED) {
                    // 员工在平台上没有首次消费分润信息
                    return new Page<>();
                }
                queryWrapper.eq(ClsMarketEtcAgentPromoteHistory::getAgentId, agent.getId());
                // 查询已激活分佣奖励
                queryWrapper.gt(ClsMarketEtcAgentPromoteHistory::getStaffBonus, 0);
                break;
            case AgentRole.AGENT_LEVEL_1:
                // 是一级代理
                switch (query.getType()) {
                    case ACTIVATED:
                        // 查询已激活分佣奖励
                        queryWrapper.gt(ClsMarketEtcAgentPromoteHistory::getActiveBonus, 0);
                        break;
                    case CONSUMED:
                        // 查询已消费分佣奖励
                        queryWrapper.gt(ClsMarketEtcAgentPromoteHistory::getFirstConsumeBonus, 0);
                        break;
                }
                queryWrapper.and(wrapper -> wrapper
                        // 查询当前代理商推广产生的分润
                        .eq(ClsMarketEtcAgentPromoteHistory::getAgentId, agent.getId())
                        .or()
                        // 查询当前代理商发展的下级推广产生的分润
                        .eq(ClsMarketEtcAgentPromoteHistory::getAgent1Id, agent.getId()));
                break;
            case AgentRole.AGENT_LEVEL_2:
                // 是二级代理,在平台上没有分润信息
            case AgentRole.AGENT_LEVEL_3:
                // 是三级代理,在平台上没有分润信息
                return new Page<>();
        }
        // 按id倒序
        queryWrapper.orderByDesc(ClsMarketEtcAgentPromoteHistory::getId);
        IPage<ClsMarketEtcAgentPromoteHistory> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = agentPromoteHistoryDao.selectPage(page, queryWrapper);
        List<PromoteAppDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<PromoteAppDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }

    private PromoteAppDto toDto(ClsMarketEtcAgentPromoteHistory entity) {
        PromoteAppDto dto = DtoService.INSTANCE.toAppDto(entity);
        dto.setMethodName(entity.getMethod().getDescribe());
        if (entity.getAgentId() != null) {
            Optional.ofNullable(agentDao.findOne(entity.getAgentId()))
                    .ifPresent(t -> {
                        dto.setAgentRealName(t.getRealName());
                        dto.setAgentRole(t.getRole());
                        dto.setAgentRoleName(t.getRole().getDescribe());
                    });
        }
        return dto;
    }
}
