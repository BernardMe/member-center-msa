package com.zzjdyf.market.service.app;

import com.zzjdyf.common.exception.BizCodeEnum;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.common.LoginInfo;
import com.zzjdyf.market.domain.dao.AgentDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.domain.entity.enums.AgentAuditStatus;
import com.zzjdyf.market.service.common.JwtService;
import com.zzjdyf.market.service.common.SmsService;
import com.zzjdyf.market.vo.command.SmsLoginCommand;
import com.zzjdyf.market.vo.dto.AgentAppDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author 阿隆
 * Created on 2021/7/20 3:30 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class LoginAppService {
    private final AgentDao agentDao;
    private final JwtService jwtService;
    private final SmsService smsService;
    private final AgentAppService agentAppService;

    /**
     * 手机号短信登陆
     *
     * @param command 命令 {@link SmsLoginCommand}
     * @return 代理商信息 {@link AgentAppDto}
     */
    public AgentAppDto login(SmsLoginCommand command) {
        // 校验短信验证码
        smsService.verify(command.getPhone(), command.getSmsCode());
        // 获取代理商信息
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOneByPhone(command.getPhone()))
                .orElseThrow(() -> new RRException(BizCodeEnum.USER_NOT_EXIST));
        if (agent.getAuditStatus() == AgentAuditStatus.WAIT_AUDIT) {
            throw new RRException("您的代理商资格正在审核中");
        }
        if (agent.getAuditStatus() == AgentAuditStatus.DENIED) {
            throw new RRException("您的代理商资格未通过审核,失败原因:" + agent.getRefuseReason());
        }
        // 该代理商存在
        AgentAppDto dto = agentAppService.toDto(agent);
        try {
            // 生成token
            LoginInfo loginInfo = LoginInfo.builder().id(agent.getId()).phone(agent.getPhone()).build();
            dto.setToken(jwtService.generate(loginInfo));
            return dto;
        } catch (Exception e) {
            throw new RRException(BizCodeEnum.LOGIN_FAILED, e);
        }
    }


    /**
     * 微信授信登陆
     *
     * @param phone 命令 {@link String}
     * @return 代理商信息 {@link AgentAppDto}
     */
    public AgentAppDto loginByphone(String phone) {

        // 获取代理商信息
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOneByPhone(phone))
                .orElseThrow(() -> new RRException(BizCodeEnum.USER_NOT_EXIST));
        if (agent.getAuditStatus() == AgentAuditStatus.WAIT_AUDIT) {
            throw new RRException("您的代理商资格正在审核中");
        }
        if (agent.getAuditStatus() == AgentAuditStatus.DENIED) {
            throw new RRException("您的代理商资格未通过审核,失败原因:" + agent.getRefuseReason());
        }
        // 该代理商存在
        AgentAppDto dto = agentAppService.toDto(agent);
        try {
            // 生成token
            LoginInfo loginInfo = LoginInfo.builder().id(agent.getId()).phone(agent.getPhone()).build();
            dto.setToken(jwtService.generate(loginInfo));
            return dto;
        } catch (Exception e) {
            throw new RRException(BizCodeEnum.LOGIN_FAILED, e);
        }
    }
}
