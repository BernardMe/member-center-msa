package com.cheshun.market.service.admin;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.PhoneUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.enums.AgentAuditStatus;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.domain.entity.enums.GeneralStatus;
import com.cheshun.market.service.DtoService;
import com.cheshun.market.vo.command.*;
import com.cheshun.common.exception.BizCodeEnum;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.config.common.LoginInfo;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.vo.dto.AgentAdminDto;
import com.cheshun.market.vo.dto.AgentDashboardAdminVo;
import com.cheshun.market.vo.query.AgentPageQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author 阿隆
 * Created on 2021/7/20 9:07 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AgentAdminService {
    private final AgentDao agentDao;

    /**
     * 代理数量概况统计查询
     *
     * @return  {@link AgentDashboardAdminVo}
     */
    public AgentDashboardAdminVo dashboard() {
        LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgent::getRole, AgentRole.AGENT_LEVEL_1);
        queryWrapper.in(ClsMarketEtcAgent::getAuditStatus, AgentAuditStatus.PASSED, AgentAuditStatus.PASSED);
        int agent1Count = Optional.ofNullable(agentDao.getMapper().selectCount(queryWrapper)).orElse(0);
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgent::getRole, AgentRole.AGENT_LEVEL_2);
        queryWrapper.in(ClsMarketEtcAgent::getAuditStatus, AgentAuditStatus.PASSED, AgentAuditStatus.PASSED);
        int agent2Count = Optional.ofNullable(agentDao.getMapper().selectCount(queryWrapper)).orElse(0);
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClsMarketEtcAgent::getRole, AgentRole.AGENT_LEVEL_3);
        queryWrapper.in(ClsMarketEtcAgent::getAuditStatus, AgentAuditStatus.PASSED, AgentAuditStatus.PASSED);
        int agent3Count = Optional.ofNullable(agentDao.getMapper().selectCount(queryWrapper)).orElse(0);
        AgentDashboardAdminVo vo = new AgentDashboardAdminVo();
        vo.setAgent1Count(agent1Count);
        vo.setAgent2Count(agent2Count);
        vo.setAgent3Count(agent3Count);
        return vo;
    }


    /**
     * 添加员工
     *
     * @param command {@link AddStaffCommand}
     */
    public void addStaff(AddStaffCommand command) {
        if (!PhoneUtil.isMobile(command.getPhone())) {
            throw new RRException("手机号格式不正确");
        }
        // 查询该员工是否存在
        ClsMarketEtcAgent agent = agentDao.findOneByPhone(command.getPhone());
        if (agent != null) {
            // 该员工已存在
            throw new RRException(BizCodeEnum.USER_EXIST);
        }
        // 该员工信息不存在,给该员工创建代理商信息
        agent = new ClsMarketEtcAgent();
        agent.setRealName(command.getName());
        agent.setPhone(command.getPhone());
        // 设置为员工角色
        agent.setRole(AgentRole.STAFF);
        // 员工默认为审核通过
        agent.setAuditStatus(AgentAuditStatus.PASSED);
        agent.setStatus(GeneralStatus.ENABLED);
        // 保存员工信息
        if (agentDao.save(agent) == null) {
            throw new RRException(BizCodeEnum.ADD_FAILED);
        }
    }

    /**
     * 更新代理商
     *
     * @param command {@link UpdateAgentCommand}
     */
    public void update(UpdateAgentCommand command) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(command.getId()))
                .orElseThrow(() -> new RRException("代理商不存在"));
        Optional.ofNullable(command.getName())
                .filter(StringUtils::hasText)
                .ifPresent(agent::setRealName);
        Optional.ofNullable(command.getPhone())
                .filter(StringUtils::hasText)
                .filter(PhoneUtil::isMobile)
                .ifPresent(agent::setPhone);
        Optional.ofNullable(command.getEnableWithdraw())
                .ifPresent(agent::setEnableWithdraw);
        agentDao.save(agent);
    }

    /**
     * 分页查询代理商
     *
     * @param query 条件 {@link AgentPageQuery}
     * @return 代理商列表  {@link IPage< AgentAdminDto >}
     */
    public IPage<AgentAdminDto> query(AgentPageQuery query) {

        LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(query.getRealName())) {
            queryWrapper.eq(ClsMarketEtcAgent::getRealName, query.getRealName());
        }
        if (StringUtils.hasText(query.getPhone())) {
            queryWrapper.eq(ClsMarketEtcAgent::getPhone, query.getPhone());
        }
        if (query.getRole() != null) {
            queryWrapper.eq(ClsMarketEtcAgent::getRole, query.getRole());
        }
        if (query.getAuditStatus() != null) {
            queryWrapper.eq(ClsMarketEtcAgent::getAuditStatus, query.getAuditStatus());
        }
        queryWrapper.orderByDesc(ClsMarketEtcAgent::getCreateTime);
        IPage<ClsMarketEtcAgent> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = agentDao.selectPage(page, queryWrapper);
        List<AgentAdminDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<AgentAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }



    private AgentAdminDto toDto(ClsMarketEtcAgent entity) {
        AgentAdminDto dto = DtoService.INSTANCE.toDto(entity);
        Optional.ofNullable(entity.getIdNo())
                .filter(StringUtils::hasText)
                .map(t -> DesensitizedUtil.idCardNum(t, 4, 6))
                .ifPresent(dto::setIdNo);
        return dto;
    }

    /**
     * 审核代理商
     *
     * @param command {@link AuditAgentCommand}
     */
    public void audit(AuditAgentCommand command) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(command.getId()))
                .orElseThrow(() -> new RRException("代理商不存在"));
        if (agent.getAuditStatus() == AgentAuditStatus.DENIED) {
            throw new RRException("无需审核,该代理商已审核未通过,失败原因:" + agent.getRefuseReason());
        }
        if (agent.getAuditStatus() == AgentAuditStatus.PASSED) {
            throw new RRException("无需审核,该代理商已审核通过");
        }
        if (agent.getAuditStatus() == AgentAuditStatus.APPLY_TERMINATE) {
            throw new RRException("无需审核,该代理商已申请解约");
        }
        if (agent.getAuditStatus() == AgentAuditStatus.TERMINATED) {
            throw new RRException("无需审核,该代理商已解约");
        }
        boolean pass = Optional.ofNullable(command.getPass()).orElse(false);
        if (pass) {
            // 审核通过
            agent.setAuditStatus(AgentAuditStatus.PASSED);
        } else {
            // 审核不通过
            agent.setAuditStatus(AgentAuditStatus.DENIED);
            agent.setRefuseReason(command.getRefuseReason());
        }
        agentDao.save(agent);
    }

    /**
     * 同意代理商的解约申请
     *
     * @param command {@link TerminatedCommand}
     */
    public void terminated(TerminatedCommand command) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(command.getId()))
                .orElseThrow(() -> new RRException("代理商不存在"));
        if (agent.getAuditStatus() == AgentAuditStatus.WAIT_AUDIT) {
            throw new RRException("该代理商还未通过审核");
        }
        if (agent.getAuditStatus() == AgentAuditStatus.DENIED) {
            throw new RRException("该代理商未通过审核,失败原因:" + agent.getRefuseReason());
        }
        if (agent.getAuditStatus() == AgentAuditStatus.PASSED) {
            throw new RRException("该代理商还未申请解约");
        }
        if (agent.getAuditStatus() == AgentAuditStatus.TERMINATED) {
            throw new RRException("该代理商已解约");
        }
        // 解约
        agent.setAuditStatus(AgentAuditStatus.TERMINATED);
        agentDao.save(agent);
    }

    /**
     * 拒绝代理商的解约申请
     *
     * @param command {@link TerminatedCommand}
     */
    public void refuseTerminated(TerminatedCommand command) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(command.getId()))
                .orElseThrow(() -> new RRException("代理商不存在"));
        if (agent.getAuditStatus() == AgentAuditStatus.WAIT_AUDIT) {
            throw new RRException("该代理商还未通过审核");
        }
        if (agent.getAuditStatus() == AgentAuditStatus.DENIED) {
            throw new RRException("该代理商未通过审核,失败原因:" + agent.getRefuseReason());
        }
        if (agent.getAuditStatus() == AgentAuditStatus.PASSED) {
            throw new RRException("该代理商还未申请解约");
        }
        if (agent.getAuditStatus() == AgentAuditStatus.TERMINATED) {
            throw new RRException("该代理商已解约");
        }
        // 拒绝解约-还原回已通过状态
        agent.setAuditStatus(AgentAuditStatus.PASSED);
        agentDao.save(agent);
    }


    /**
     * 根据数据权限，统计代理数量
     *
     * @return  {@link AgentDashboardAdminVo}
     */
    public AgentDashboardAdminVo dashboardPersonal( LoginInfo loginInfo) {

        //判断登录人的手机号在供应商是否有数据，无数据返回空，有数据返回登录人及下级供应商的数据
        ClsMarketEtcAgent clsMarketEtcAgent = agentDao.findOneByPhone(loginInfo.getPhone());
        if(clsMarketEtcAgent==null){
            return new AgentDashboardAdminVo();
        }
        List<ClsMarketEtcAgent> clsMarketEtcAgents =new ArrayList<>();
        clsMarketEtcAgents.add(clsMarketEtcAgent);
        //查询下级代理商的数据
        clsMarketEtcAgents = findChildList(clsMarketEtcAgent,clsMarketEtcAgents,new AgentPageQuery());

        //对数据进行去重
        clsMarketEtcAgents = clsMarketEtcAgents.stream().distinct().collect(Collectors.toList());

        AgentDashboardAdminVo vo = new AgentDashboardAdminVo();

        //构造结果Map，并遍历List得出结果
        Map<String,Integer> groupList = new HashMap<String,Integer>();
        for(ClsMarketEtcAgent marketEtcAgent:clsMarketEtcAgents){
            String key = marketEtcAgent.getRole().getDescribe();
            if(!groupList.containsKey(key)){
                groupList.put(key, 0);
            }
            groupList.put(key, groupList.get(key)+1);
        }

        for(String key:groupList.keySet()){
            if(key.equals(AgentRole.AGENT_LEVEL_1.getDescribe())){
                vo.setAgent1Count(groupList.get(AgentRole.AGENT_LEVEL_1.getDescribe()));
            }
            if(key.equals(AgentRole.AGENT_LEVEL_2.getDescribe())){
                vo.setAgent2Count(groupList.get(AgentRole.AGENT_LEVEL_2.getDescribe()));
            }
            if(key.equals(AgentRole.AGENT_LEVEL_3.getDescribe())){
                vo.setAgent3Count(groupList.get(AgentRole.AGENT_LEVEL_3.getDescribe()));
            }

        }


        return vo;
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

        List<ClsMarketEtcAgent> clsMarketEtcAgents =new ArrayList<>();
        //判断是否增加自己的数据
        clsMarketEtcAgents = addAgent(clsMarketEtcAgent,clsMarketEtcAgents,query);
        //查询下级代理商的数据
        clsMarketEtcAgents = findChildList(clsMarketEtcAgent,clsMarketEtcAgents,query);
        //判断查询到的数据为空的时候，直接返回空
        if(clsMarketEtcAgents.size()==0){
            return new Page<AgentAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(null);
        }
        //对数据进行去重
        clsMarketEtcAgents = clsMarketEtcAgents.stream().distinct().collect(Collectors.toList());
        page.setTotal(clsMarketEtcAgents.size());
        //对数据进行排序
        clsMarketEtcAgents = clsMarketEtcAgents.stream().sorted(Comparator.comparing(ClsMarketEtcAgent::getCreateTime).reversed()).collect(Collectors.toList());

        clsMarketEtcAgents =pageBySubList(clsMarketEtcAgents,query.getPageSize(),query.getPageNum());

        page.setRecords(clsMarketEtcAgents);
        List<AgentAdminDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<AgentAdminDto>(query.getPageNum(), query.getPageSize(), page.getTotal()).setRecords(list);
    }


    //利用递归查询下级数据
    public List<ClsMarketEtcAgent> findChildList(ClsMarketEtcAgent clsMarketEtcAgent,List<ClsMarketEtcAgent> clsMarketEtcAgents,AgentPageQuery query){
        if(AgentRole.AGENT_LEVEL_3!=clsMarketEtcAgent.getRole()){
            LambdaQueryWrapper<ClsMarketEtcAgent> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.hasText(query.getRealName())) {
                queryWrapper.eq(ClsMarketEtcAgent::getRealName, query.getRealName());
            }
            if (StringUtils.hasText(query.getPhone())) {
                queryWrapper.eq(ClsMarketEtcAgent::getPhone, query.getPhone());
            }
            if (query.getRole() != null) {
                queryWrapper.eq(ClsMarketEtcAgent::getRole, query.getRole());
            }
            if (query.getAuditStatus() != null) {
                queryWrapper.eq(ClsMarketEtcAgent::getAuditStatus, query.getAuditStatus());
            }
            queryWrapper.orderByDesc(ClsMarketEtcAgent::getCreateTime);
            if(AgentRole.STAFF == clsMarketEtcAgent.getRole()){
                queryWrapper.eq(ClsMarketEtcAgent::getStaffId,clsMarketEtcAgent.getId());
            }else if(AgentRole.AGENT_LEVEL_1 ==clsMarketEtcAgent.getRole()){
                queryWrapper.eq(ClsMarketEtcAgent::getAgent1Id,clsMarketEtcAgent.getId());
            }else if(AgentRole.AGENT_LEVEL_2 ==clsMarketEtcAgent.getRole()){
                queryWrapper.eq(ClsMarketEtcAgent::getAgent2Id,clsMarketEtcAgent.getId());
            }
            List<ClsMarketEtcAgent>  list = agentDao.selectList(queryWrapper);
            clsMarketEtcAgents.addAll(list);

        }
        return clsMarketEtcAgents;
    }


    /**
     * 利用subList方法进行分页
     * @param list 分页数据
     * @param pagesize  页面大小
     * @param currentPage   当前页面
     */
    private List<ClsMarketEtcAgent> pageBySubList(List<ClsMarketEtcAgent> list, int pagesize, int currentPage) {
        List<ClsMarketEtcAgent> subList=null;
        if(list.size()>0 && pagesize!=0 && currentPage!=0){
            int totalcount = list.size();
            int pagecount = 0;

            if(pagesize > totalcount){//页面数量大于数据总数直接返回全部数据
                return list;
            }
            else {
                int m = totalcount % pagesize;//最后的余数
                if (m > 0) {
                    pagecount = totalcount / pagesize + 1;
                } else {
                    pagecount = totalcount / pagesize;
                }
                if (m == 0) {
                    subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
                } else {
                    if (currentPage == pagecount) {
                        subList = list.subList((currentPage - 1) * pagesize, totalcount);
                    } else {
                        subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
                    }
                }
            }}
        return subList;
    }

    //判断是否需要显示自己的数据
    public List<ClsMarketEtcAgent> addAgent(ClsMarketEtcAgent clsMarketEtcAgent,List<ClsMarketEtcAgent> clsMarketEtcAgents,AgentPageQuery query){
        if (StringUtils.hasText(query.getRealName())&&!query.getRealName().equals(clsMarketEtcAgent.getRealName())) {
            return clsMarketEtcAgents;
        }
        if (StringUtils.hasText(query.getPhone())&&!query.getPhone().equals(clsMarketEtcAgent.getPhone())) {
            return clsMarketEtcAgents;
        }
        if (query.getRole() != null&&query.getRole() != clsMarketEtcAgent.getRole().getValue()) {
            return clsMarketEtcAgents;
        }
        if (query.getAuditStatus() != null &&query.getAuditStatus() !=clsMarketEtcAgent.getAuditStatus()) {
            return clsMarketEtcAgents;
        }
        clsMarketEtcAgents.add(clsMarketEtcAgent);
        return clsMarketEtcAgents;

    }


    public void batchUpdate(UpdateBatchAgentCommand updateBatchAgentCommand){
        log.info("批量更新数据，每20条处理一次");
        if(updateBatchAgentCommand.getAgentIds().size()==0){
            throw new RRException("请选择代理商");
        }
        if(StringUtils.isEmpty(updateBatchAgentCommand.getEnableWithdraw())){
            throw new RRException("请选择禁止或者允许提现");
        }
        if(StringUtils.isEmpty(updateBatchAgentCommand.getRemarks())){
            throw new RRException("请输入原因备注");
        }
        try {
            batchDeal(updateBatchAgentCommand.getAgentIds(), 20,updateBatchAgentCommand.getRemarks(),updateBatchAgentCommand.getEnableWithdraw());
        }catch (Exception e){
            log.info("批量更新数据失败");
        }
    }


    public void batchDeal(List data, int batchNum,String remarks,Integer enableWithdraw) throws InterruptedException {
        int totalNum = data.size();
        int pageNum = totalNum % batchNum == 0 ? totalNum / batchNum : totalNum / batchNum + 1;
        ExecutorService executor = Executors.newFixedThreadPool(pageNum);
        try {
            CountDownLatch countDownLatch = new CountDownLatch(pageNum);
            List subData = null;
            int fromIndex, toIndex;
            for (int i = 0; i < pageNum; i++) {
                fromIndex = i * batchNum;
                toIndex = Math.min(totalNum, fromIndex + batchNum);
                subData = data.subList(fromIndex, toIndex);
                ImportTask task = new ImportTask(subData, countDownLatch, remarks, enableWithdraw);
                executor.execute(task);
            }
            // 主线程必须在启动其它线程后立即调用CountDownLatch.await()方法，
            // 这样主线程的操作就会在这个方法上阻塞，直到其它线程完成各自的任务。
            // 计数器的值等于0时，主线程就能通过await()方法恢复执行自己的任务。
            countDownLatch.await();
            log.info("数据操作完成!可以在此开始其它业务");
        } finally {
            // 关闭线程池，释放资源
            executor.shutdown();
        }
    }

    class ImportTask implements Runnable {
        private List list;
        private CountDownLatch countDownLatch;
        private String remarks;
        private Integer enableWithdraw;
        public ImportTask(List data, CountDownLatch countDownLatch,String remarks,Integer enableWithdraw) {
            this.list = data;
            this.countDownLatch = countDownLatch;
            this.remarks =remarks;
            this.enableWithdraw =enableWithdraw;
        }

        @Override
        public void run() {
            if (null != list) {
                agentDao.getMapper().updateWithdrawStatus(list,remarks,enableWithdraw);
            }
            // 发出线程任务完成的信号
            countDownLatch.countDown();
        }
    }





}
