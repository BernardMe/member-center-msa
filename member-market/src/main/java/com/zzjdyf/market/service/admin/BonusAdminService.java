package com.zzjdyf.market.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.dao.AgentDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.domain.entity.enums.AgentAuditStatus;
import com.zzjdyf.market.domain.entity.enums.AgentRole;
import com.zzjdyf.market.service.DtoService;
import com.zzjdyf.market.vo.dto.AgentAdminDto;
import com.zzjdyf.market.vo.dto.BonusDashboardAdminVo;
import com.zzjdyf.market.vo.query.AgentPageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阿隆
 * Created on 2021/7/29 9:07 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class BonusAdminService {
    private final AgentDao agentDao;

    /**
     * 分润概况统计查询
     *
     * @return 分润概况统计 {@link BonusDashboardAdminVo}
     */
    public BonusDashboardAdminVo dashboard() {
        // 仅查询一级代理商角色的累计分润
        BonusDashboardAdminVo vo = agentDao.getMapper().queryBonusStatistics(AgentRole.AGENT_LEVEL_1,null,null);
        vo.setTotalBalance(vo.getTotalBonus().subtract(vo.getTotalWithdrawAmount()));
        return vo;
    }

    /**
     * 分页查询代理商分润信息
     *
     * @param query 条件 {@link AgentPageQuery}
     * @return 代理商分润信息列表  {@link IPage< AgentAdminDto >}
     */
    public IPage<AgentAdminDto> query(AgentPageQuery query) {
        LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(query.getRealName())) {
            queryWrapper.eq(ClsMarketEtcAgent::getRealName, query.getRealName());
        }
        if (StringUtils.hasText(query.getPhone())) {
            queryWrapper.eq(ClsMarketEtcAgent::getPhone, query.getPhone());
        }
        // 仅查询曾经已审核通过的代理商
        queryWrapper.in(ClsMarketEtcAgent::getAuditStatus, AgentAuditStatus.PASSED, AgentAuditStatus.APPLY_TERMINATE, AgentAuditStatus.TERMINATED);
        // 角色
        queryWrapper.eq(ClsMarketEtcAgent::getRole, query.getRole());
        queryWrapper.orderByDesc(ClsMarketEtcAgent::getTotalActiveBonus).orderByDesc(ClsMarketEtcAgent::getTotalFirstConsumeBonus);
        IPage<ClsMarketEtcAgent> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = agentDao.selectPage(page, queryWrapper);
        List<AgentAdminDto> list = page.getRecords().stream().map(DtoService.INSTANCE::toDto).collect(Collectors.toList());
        return new Page<AgentAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }



    /**
     * 根据数据权限分页查询个人权限代理商
     *
     * @param query 条件 {@link AgentPageQuery}
     * @return 代理商列表  {@link IPage<AgentAdminDto>}
     */
    public IPage<AgentAdminDto> queryPersonal(AgentPageQuery query, LoginInfo loginInfo) {
        IPage<ClsMarketEtcAgent> page = new Page<>(query.getPageNum(), query.getPageSize());
        //判断登录人的手机号在供应商是否有数据，无数据返回空，有数据返回登录人及下级供应商的数据
        ClsMarketEtcAgent clsMarketEtcAgent = agentDao.findOneByPhone(loginInfo.getPhone());
        if(clsMarketEtcAgent==null){
            return new Page<AgentAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(null);
        }
        LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(query.getRealName())) {
            queryWrapper.eq(ClsMarketEtcAgent::getRealName, query.getRealName());
        }
        if (StringUtils.hasText(query.getPhone())) {
            queryWrapper.eq(ClsMarketEtcAgent::getPhone, query.getPhone());
        }
        // 仅查询曾经已审核通过的代理商
        queryWrapper.in(ClsMarketEtcAgent::getAuditStatus, AgentAuditStatus.PASSED, AgentAuditStatus.APPLY_TERMINATE, AgentAuditStatus.TERMINATED);

        queryWrapper.orderByDesc(ClsMarketEtcAgent::getTotalActiveBonus).orderByDesc(ClsMarketEtcAgent::getTotalFirstConsumeBonus);
        //判断登录人的角色，二级和三级供应商返回分润数据为空，员工返回下级一级供应商的数据，一级供应商返回自己的数据
        if(AgentRole.AGENT_LEVEL_2==clsMarketEtcAgent.getRole()||AgentRole.AGENT_LEVEL_3==clsMarketEtcAgent.getRole()){
            return new Page<AgentAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(null);
        }else if(AgentRole.AGENT_LEVEL_1 ==clsMarketEtcAgent.getRole()){
            queryWrapper.eq(ClsMarketEtcAgent::getId,clsMarketEtcAgent.getId());
        }else{
            // 角色
            queryWrapper.eq(ClsMarketEtcAgent::getRole, AgentRole.AGENT_LEVEL_1);
            queryWrapper.eq(ClsMarketEtcAgent::getStaffId,clsMarketEtcAgent.getId());
        }
        page = agentDao.selectPage(page, queryWrapper);
        List<AgentAdminDto> list = page.getRecords().stream().map(DtoService.INSTANCE::toDto).collect(Collectors.toList());
        return new Page<AgentAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);

    }


    /**
     * 分润概况统计查询
     *
     * @return 分润概况统计 {@link BonusDashboardAdminVo}
     */
    public BonusDashboardAdminVo dashboardPersonal(LoginInfo loginInfo) {
        //判断登录人的手机号在供应商是否有数据，无数据返回空，有数据返回登录人的数据
        ClsMarketEtcAgent clsMarketEtcAgent = agentDao.findOneByPhone(loginInfo.getPhone());
        if(clsMarketEtcAgent==null){
            return new BonusDashboardAdminVo();
        }
        //判断登录人的角色，二级和三级供应商返回分润数据为空，员工返回下级一级供应商的数据，一级供应商返回自己的数据
       if(AgentRole.AGENT_LEVEL_2==clsMarketEtcAgent.getRole()||AgentRole.AGENT_LEVEL_3==clsMarketEtcAgent.getRole()){
           return new BonusDashboardAdminVo();
       }else if(AgentRole.AGENT_LEVEL_1 ==clsMarketEtcAgent.getRole()){
           BonusDashboardAdminVo vo = agentDao.getMapper().queryBonusStatistics(null,null,clsMarketEtcAgent.getId());
           return vo;
       }else{
           BonusDashboardAdminVo vo = agentDao.getMapper().queryBonusStatistics(null,clsMarketEtcAgent.getId(),null);
            return vo;
       }
    }
}
