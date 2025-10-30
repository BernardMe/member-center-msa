package com.zzjdyf.market.service.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.domain.dao.AgentPromoteHistoryDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentPromoteHistory;
import com.zzjdyf.market.domain.entity.enums.BonusStatus;
import com.zzjdyf.market.service.app.AgentAppService;
import com.zzjdyf.market.vo.command.ActiveNotifyCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 阿隆
 * Created on 2021/8/12 10:04 上午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AgentPromoteSystemService {
    private final AgentPromoteHistoryDao agentPromoteHistoryDao;
    private final RedissonClient redissonClient;
    private static final String LOCK_NAME = "ACTIVE_NOTIFY_%s_%s";

    /**
     * 设备激活结果通知
     *
     * @param command 命令 {@link ActiveNotifyCommand}
     * @return ok:成功
     */
    public String activeNotify(ActiveNotifyCommand command) {
        String notifyId = UUID.randomUUID().toString().replace("-", "");
        log.info("设备激活结果通知,{}:{}", notifyId, command);
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(String.format(LOCK_NAME, command.getActiveCarNo(), command.getActiveCarColor()));
        try {
            // 加锁
            rLock.lock(5, TimeUnit.MINUTES);
            // 加锁成功
            log.info("设备激活结果通知(已获取锁)." + notifyId);
            try {
                // 查询该激活的车牌号是否已被推广
                LambdaQueryWrapper<ClsMarketEtcAgentPromoteHistory> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ClsMarketEtcAgentPromoteHistory::getCarNo, command.getActiveCarNo());
                queryWrapper.eq(ClsMarketEtcAgentPromoteHistory::getCarColor, command.getActiveCarColor());
                // 只会存在一条数据
                if (Optional.ofNullable(agentPromoteHistoryDao.getMapper().selectCount(queryWrapper)).orElse(0) > 0) {
                    // 推广信息已存在
                    throw new RRException("该车牌号已被推广");
                }

                // 创建新推广信息
                ClsMarketEtcAgentPromoteHistory entity = new ClsMarketEtcAgentPromoteHistory();
                if (StringUtils.hasText(command.getAgentId())) {
                    try {
                        Long agentId = Long.parseLong(command.getAgentId().replace(AgentAppService.PREFIX_AGENT_ID, ""));
                        // 代理商id
                        entity.setAgentId(agentId);
                    } catch (Exception e) {
                        log.error("设备激活结果通知,解析代理商id异常,{}:{}", notifyId, command);
                    }
                }
                // 推广的用户
                entity.setUserId(command.getUserId());
                // 激活渠道
                entity.setChannel(command.getChannel());
                // 激活方式:E-用户线上申请,自己激活 S-代理商线下安装激活
                entity.setMethod(command.getMethod());
                // 激活车辆的车牌号
                entity.setCarNo(command.getActiveCarNo());
                // 激活车辆的车牌号颜色
                entity.setCarColor(command.getActiveCarColor());
                // 激活的ETC账号
                entity.setEtcAccount(command.getEtcAccount());
                // 激活的ETC卡号
                entity.setCardSn(command.getCardSn());
                // 激活的OBU标签号
                entity.setDeviceSn(command.getDeviceSn());
                // 激活时间
                entity.setActiveTime(new Date());
                // 默认状态为待结算
                entity.setActiveBonusStatus(BonusStatus.WAIT);
                // 保存推广信息
                if (agentPromoteHistoryDao.save(entity) == null) {
                    throw new RRException("保存推广信息失败", entity.toString());
                }
            } catch (Exception e) {
                log.error(String.format("设备激活结果通知处理异常,%s:%s", notifyId, command), e);
            }
            // 释放锁
            rLock.unlock();
            log.info("设备激活结果通知(已释放锁)." + notifyId);
        } catch (Exception e) {
            log.error("设备激活结果通知异常." + notifyId, e);
        }
        return "ok";
    }
}
