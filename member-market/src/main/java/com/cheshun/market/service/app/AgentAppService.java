package com.cheshun.market.service.app;

import cn.hutool.core.util.DesensitizedUtil;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.enums.AgentAuditStatus;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.domain.entity.enums.GeneralStatus;
import com.cheshun.market.service.DtoService;
import com.cheshun.market.service.YunZhangHuService;
import com.cheshun.common.exception.BizCodeEnum;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.vo.command.AddAgentCommand;
import com.cheshun.market.vo.dto.AgentAppDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author wangzhuo
 * Created on 20210725
 */
@Slf4j
@Service
@AllArgsConstructor
public class AgentAppService {
    public final static String PREFIX_AGENT_ID = "ETCMARKET_";
    private final AgentDao agentDao;
    private final YunZhangHuService yunZhangHuService;

    /**
     * 根据id查询代理商信息
     *
     * @param id 代理商id
     * @return 代理商信息 {@link AgentAppDto}
     */
    public AgentAppDto get(Long id) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        return toDto(agent);
    }

    /**
     * 新增代理
     *
     * @param command {@link AddAgentCommand}
     * @return 代理商信息 {@link AgentAppDto}
     */
    public AgentAppDto add(AddAgentCommand command) {
        // 查询邀请人是否存在
        ClsMarketEtcAgent inviter = Optional.ofNullable(agentDao.findOne(command.getInviterId()))
                .orElseThrow(() -> new RRException("邀请人不存在"));
        // 校验该用户是否已成为代理商
        ClsMarketEtcAgent agent = agentDao.findOneByPhone(command.getPhone());
        if (agent != null) {
            // 该用户代理商信息存在
            if (agent.getAuditStatus() == AgentAuditStatus.WAIT_AUDIT) {
                throw new RRException("您的代理商资格正在审核中");
            }
            if (agent.getAuditStatus() == AgentAuditStatus.PASSED ||
                    agent.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE) {
                throw new RRException("您已经是代理商了");
            }
            if (agent.getAuditStatus() == AgentAuditStatus.TERMINATED) {
                throw new RRException("您已解约，暂时不可重新申请代理商资格");
            }
        } else {
            // 给该用户创建代理商信息
            agent = new ClsMarketEtcAgent();
        }
        // 调用云账户API进行实名认证
        yunZhangHuService.realNameVerify(command.getIdCard(), command.getName());
        agent.setPhone(command.getPhone());
        agent.setRealName(command.getName());
        agent.setIdNo(command.getIdCard());
        // 设置角色
        switch (inviter.getRole()) {
            case AgentRole.STAFF:
                // 邀请人是【员工】
                agent.setStaffId(inviter.getId());
                // 角色设置为【一级代理商】
                agent.setRole(AgentRole.AGENT_LEVEL_1);
                break;
            case AgentRole.AGENT_LEVEL_1:
                // 邀请人是【一级代理商】
                agent.setStaffId(inviter.getStaffId());
                agent.setAgent1Id(inviter.getId());
                // 角色设置为【二级代理商】
                agent.setRole(AgentRole.AGENT_LEVEL_2);
                break;
            case AgentRole.AGENT_LEVEL_2:
                // 邀请人是【二级代理商】
                agent.setStaffId(inviter.getStaffId());
                agent.setAgent1Id(inviter.getAgent1Id());
                agent.setAgent2Id(inviter.getId());
                // 角色设置为【三级代理商】
                agent.setRole(AgentRole.AGENT_LEVEL_3);
                break;
            case AgentRole.AGENT_LEVEL_3:
                throw new RRException("您的邀请人无邀请权限");
        }
        // 默认为待审核
        agent.setAuditStatus(AgentAuditStatus.WAIT_AUDIT);
        agent.setStatus(GeneralStatus.ENABLED);
        // 保存代理商信息
        agent = agentDao.save(agent);
        if (agent == null) {
            throw new RRException(BizCodeEnum.ADD_FAILED);
        }
        return toDto(agent);
    }

    /**
     * 代理商申请解约
     *
     * @param id 代理商id
     * @apiNote 代理商申请解约后, 代理商手中库存卡品仍然执行正常营销分润逻辑，且不限制分润提现，但不能创建订货/补货订单
     */
    public void terminate(Long id) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .filter(t -> t.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE || t.getAuditStatus() == AgentAuditStatus.PASSED)
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (agent.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE) {
            throw new RRException("您已申请解约,不可执行该操作");
        }
        //设置代理商状态为申请注销
        agent.setAuditStatus(AgentAuditStatus.APPLY_TERMINATE);
        agentDao.save(agent);
    }

    public AgentAppDto toDto(ClsMarketEtcAgent entity) {
        AgentAppDto dto = DtoService.INSTANCE.toAppDto(entity);
        dto.setAgentId(PREFIX_AGENT_ID + entity.getId());
        dto.setAuditStatusName(entity.getAuditStatus().getDescribe());
        // 身份证号反显加密
        Optional.ofNullable(entity.getIdNo())
                .filter(StringUtils::hasText)
                .map(t -> DesensitizedUtil.idCardNum(t, 4, 6))
                .ifPresent(dto::setIdNo);
        dto.setRoleName(entity.getRole().getDescribe());
        dto.setTotalBonus(entity.getTotalActiveBonus().add(entity.getTotalFirstConsumeBonus()));
        dto.setBalance(entity.getTotalActiveBonus().add(entity.getTotalFirstConsumeBonus()).subtract(entity.getTotalWithdrawAmount()));
        // 只有一级代理商可提现
        dto.setCanWithdraw(entity.getRole() == AgentRole.AGENT_LEVEL_1);
        return dto;
    }
}
