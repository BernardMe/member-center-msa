package com.cheshun.market.service.app;

import cn.hutool.core.util.DesensitizedUtil;
import com.cheshun.market.domain.entity.ClsMarketEtcAgent;
import com.cheshun.market.domain.entity.ClsMarketEtcAgentBankCard;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.service.DtoService;
import com.cheshun.market.service.YunZhangHuService;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.domain.dao.AgentBankCardDao;
import com.cheshun.market.domain.dao.AgentDao;
import com.cheshun.market.vo.command.AddBankCardCommand;
import com.cheshun.market.vo.command.DeleteBankCardCommand;
import com.cheshun.market.vo.dto.AgentBankCardDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 阿隆
 * Created on 2021/8/4 2:13 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AgentBankCardAppService {
    private final AgentDao agentDao;
    private final AgentBankCardDao agentBankCardDao;
    private final YunZhangHuService yunZhangHuService;

    /**
     * 添加银行卡
     *
     * @param id      代理商id
     * @param command 命令 {@link AddBankCardCommand}
     */
    public void add(Long id, AddBankCardCommand command) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        if (agent.getRole() != AgentRole.AGENT_LEVEL_1) {
            // 不是一级代理商,无法绑卡
            throw new RRException("您无绑卡权限");
        }
        // 校验代理商是否已绑定该卡
        List<ClsMarketEtcAgentBankCard> bankCardList = agentBankCardDao.findAllByAgent(agent.getId());
        if (!bankCardList.isEmpty()) {
            if (bankCardList.stream().anyMatch(t -> t.getCardNo().equals(command.getCardNo()))) {
                throw new RRException("您已绑定该银行卡");
            }
        }
        // 调用云账户API获取银行信息
        Map<String, Object> bankInfo = yunZhangHuService.bankCardInfoQuery(command.getCardNo());
        // bank_code 银行代码 String 银行代码
        // bank_name 银行名称 String 银行名称
        // card_type 卡类型 String 卡类型
        // is_support 云账户是否支持向该银行打款 Boolean 云账户是否支持向该银行打款
        if (!Boolean.parseBoolean(bankInfo.get("is_support").toString())) {
            // 云账户不支持向该银行打款
            throw new RRException("不支持该银行卡");
        }
        // 设置所属银行名称
        String bankName = bankInfo.get("bank_name").toString();
        // 调用云账户API做银行卡三要素校验
        yunZhangHuService.threeFactorVerify(agent.getIdNo(), agent.getRealName(), command.getCardNo());
        // 银行卡四要素校验成功
        // 创建绑卡信息
        ClsMarketEtcAgentBankCard bankCard = new ClsMarketEtcAgentBankCard();
        bankCard.setAgentId(agent.getId());
        bankCard.setIdCard(agent.getIdNo());
        bankCard.setRealName(agent.getRealName());
        bankCard.setBankName(bankName);
        bankCard.setCardNo(command.getCardNo());
        bankCard.setPhoneNum(agent.getPhone());
        if (agentBankCardDao.save(bankCard) == null) {
            throw new RRException("添加银行卡失败");
        }
    }

    /**
     * 删除银行卡
     *
     * @param id      代理商id
     * @param command 命令 {@link DeleteBankCardCommand}
     */
    public void delete(Long id, DeleteBankCardCommand command) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        // 校验代理商是否已绑定该卡
        List<ClsMarketEtcAgentBankCard> bankCardList = agentBankCardDao.findAllByAgent(agent.getId());
        if (bankCardList.isEmpty()) {
            throw new RRException("删除失败,您还未绑定银行卡");
        }
        ClsMarketEtcAgentBankCard bankCard = bankCardList.stream()
                .filter(t -> t.getId().equals(command.getId()))
                .findFirst()
                .orElseThrow(() -> new RRException("删除失败,不是您的银行卡"));
        // 删除该卡
        agentBankCardDao.delete(bankCard);
    }

    /**
     * 查询银行卡列表
     *
     * @param id 代理商id
     * @return 银行卡列表 {@link List< AgentBankCardDto >}
     */
    public List<AgentBankCardDto> query(Long id) {
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOne(id))
                .orElseThrow(() -> new RRException("您不是代理商"));
        // 校验代理商是否已绑定该卡
        List<ClsMarketEtcAgentBankCard> bankCardList = agentBankCardDao.findAllByAgent(agent.getId());
        return bankCardList.stream().map(this::toDto).collect(Collectors.toList());
    }

    private AgentBankCardDto toDto(ClsMarketEtcAgentBankCard entity) {
        AgentBankCardDto dto = DtoService.INSTANCE.toAppDto(entity);
        Optional.ofNullable(entity.getCardNo())
                .filter(StringUtils::hasText)
                .map(DesensitizedUtil::bankCard)
                .ifPresent(dto::setCardNo);
        return dto;
    }
}
