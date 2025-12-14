package com.cheshun.market.service.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.service.DtoService;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.vo.dto.AgentAppDto;
import com.cheshun.market.vo.query.MemberPageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cheshun.market.domain.entity.enums.AgentRole.*;

@Slf4j
@Service
@AllArgsConstructor
public class MemberAppService {
    private final AgentDao agentDao;

    /**
     * 分页查询下级成员列表
     *
     * @param id    代理商Id
     * @param query 条件 {@link MemberPageQuery}
     * @return 下级成员列表 {@link IPage< AgentAppDto >}
     */
    public IPage<AgentAppDto> query(Long id, MemberPageQuery query) {
        // 检测用户是否是代理商
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (query.getAgentId() != null && !query.getAgentId().equals(agent.getId())) {
            // 校验要查看的代理商是否是当前代理商的下级
            ClsMarketEtcAgent member = Optional.ofNullable(agentDao.findOne(query.getAgentId()))
                    .orElseThrow(() -> new RRException("该用户不是代理商"));
            if (!member.getStaffId().equals(agent.getId()) &&
                    !member.getAgent1Id().equals(agent.getId()) &&
                    !member.getAgent2Id().equals(agent.getId())) {
                throw new RRException("该用户不是您的下级代理商");
            }
            // 当前代理商信息切换为要查询的下级成员
            agent = member;
        }
        LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
        switch (agent.getRole()) {
            case STAFF:
                // 当前代理商是员工
                queryWrapper.eq(ClsMarketEtcAgent::getStaffId, agent.getId());
                break;
            case AGENT_LEVEL_1:
                // 当前代理商是一级代理商
                queryWrapper.eq(ClsMarketEtcAgent::getAgent1Id, agent.getId());
                break;
            case AGENT_LEVEL_2:
                // 当前代理商是二级代理商
                queryWrapper.eq(ClsMarketEtcAgent::getAgent2Id, agent.getId());
                break;
            case AGENT_LEVEL_3:
                // 当前代理商是三级代理商,返回空列表
                return new Page<>();
        }
        queryWrapper.orderByAsc(ClsMarketEtcAgent::getId);
        IPage<ClsMarketEtcAgent> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = agentDao.selectPage(page, queryWrapper);
        List<AgentAppDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<AgentAppDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }

    private AgentAppDto toDto(ClsMarketEtcAgent entity) {
        AgentAppDto dto = DtoService.INSTANCE.toAppDto(entity);
        dto.setRoleName(entity.getRole().getDescribe());
        dto.setAuditStatusName(entity.getAuditStatus().getDescribe());
        return dto;
    }
}
