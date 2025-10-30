package com.zzjdyf.market.service.admin;

import com.zzjdyf.market.domain.dao.AgentDao;
import com.zzjdyf.market.domain.dao.AgentDayStatsDao;
import com.zzjdyf.market.domain.dao.DayStatsDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgentDayStats;
import com.zzjdyf.market.domain.entity.ClsMarketEtcDayStats;
import com.zzjdyf.market.vo.dto.StatsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author 阿隆
 * Created on 2021/9/29 5:54 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class StatsService {
    private final AgentDao agentDao;
    private final AgentDayStatsDao agentDayStatsDao;
    private final DayStatsDao dayStatsDao;
    private final RedissonClient redissonClient;
    private static final String DAY_STATS_LOCK_NAME = "DAY_STATS";
    private static final String AGENT_DAY_STATS_LOCK_NAME = "AGENT_DAY_STATS";

    /**
     * 查询统计信息
     *
     * @param queryType 查询类型 D｜W｜M｜Y
     * @return 统计信息 {@link StatsDto}
     */
    public StatsDto query(String queryType) {
        StatsDto dto = new StatsDto();
        StatsDto.Stats stats = new StatsDto.Stats();
        dto.setGeneral(stats);

        LocalDate nowDate = LocalDate.now();
        LocalDateTime fromLocalDateTime;
        LocalDateTime toLocalDateTime;
        LocalDateTime prevFromLocalDateTime;
        LocalDateTime prevToLocalDateTime;
        LocalDateTime morePrevFromLocalDateTime;
        LocalDateTime morePrevToLocalDateTime;
        switch (queryType) {
            case "D":
                fromLocalDateTime = nowDate.atStartOfDay().plusDays(-1);
                toLocalDateTime = fromLocalDateTime.plusDays(1).plusSeconds(-1);
                prevFromLocalDateTime = fromLocalDateTime.plusDays(-1);
                prevToLocalDateTime = toLocalDateTime.plusDays(-1);
                morePrevFromLocalDateTime = prevFromLocalDateTime.plusDays(-1);
                morePrevToLocalDateTime = prevToLocalDateTime.plusDays(-1);
                break;
            case "W":
                fromLocalDateTime = nowDate.atStartOfDay().plusDays(-(nowDate.getDayOfWeek().getValue() - 1));
                toLocalDateTime = fromLocalDateTime.plusDays(7).plusSeconds(-1);
                prevFromLocalDateTime = fromLocalDateTime.plusWeeks(-1);
                prevToLocalDateTime = toLocalDateTime.plusWeeks(-1);
                morePrevFromLocalDateTime = prevFromLocalDateTime.plusWeeks(-1);
                morePrevToLocalDateTime = prevToLocalDateTime.plusWeeks(-1);
                break;
            case "M":
                fromLocalDateTime = nowDate.atStartOfDay().plusDays(-(nowDate.getDayOfMonth() - 1));
                toLocalDateTime = fromLocalDateTime.plusDays(nowDate.lengthOfMonth()).plusSeconds(-1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.now().minusMonths(1);
                String prevFromStr = dateTime.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).format(formatter);
                String prevToStr = dateTime.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).format(formatter);
                prevFromLocalDateTime = LocalDateTime.parse(prevFromStr, formatter);
                prevToLocalDateTime = LocalDateTime.parse(prevToStr, formatter);
                morePrevFromLocalDateTime = prevFromLocalDateTime.plusMonths(-1);
                morePrevToLocalDateTime = prevToLocalDateTime.plusMonths(-1);
                break;
            case "Y":
                fromLocalDateTime = nowDate.atStartOfDay().plusDays(-(nowDate.getDayOfYear() - 1));
                toLocalDateTime = fromLocalDateTime.plusDays(nowDate.lengthOfYear()).plusSeconds(-1);
                prevFromLocalDateTime = fromLocalDateTime.plusYears(-1);
                prevToLocalDateTime = toLocalDateTime.plusYears(-1);
                morePrevFromLocalDateTime = prevFromLocalDateTime.plusYears(-1);
                morePrevToLocalDateTime = prevToLocalDateTime.plusYears(-1);
                break;
            default:
                dto.setAgentRank(new ArrayList<>(0));
                return dto;
        }
        // 查询统计概况
        Date fromDate = Date.from(fromLocalDateTime.toInstant(ZoneOffset.of("+8")));
        Date toDate = Date.from(toLocalDateTime.toInstant(ZoneOffset.of("+8")));
        ClsMarketEtcDayStats currentDayStats = dayStatsDao.findAllByDateRange(fromDate, toDate);

        Date prevFromDate = Date.from(prevFromLocalDateTime.toInstant(ZoneOffset.of("+8")));
        Date prevToDate = Date.from(prevToLocalDateTime.toInstant(ZoneOffset.of("+8")));
        ClsMarketEtcDayStats prevDayStats = dayStatsDao.findAllByDateRange(prevFromDate, prevToDate);

        Date morePrevFromDate = Date.from(morePrevFromLocalDateTime.toInstant(ZoneOffset.of("+8")));
        Date morePrevToDate = Date.from(morePrevToLocalDateTime.toInstant(ZoneOffset.of("+8")));
        ClsMarketEtcDayStats morePrevDayStats = dayStatsDao.findAllByDateRange(morePrevFromDate, morePrevToDate);

        int prevTotalAgentCount = 0;
        int prevTotalCardCount = 0;
        if (prevDayStats != null) {
            prevTotalAgentCount = prevDayStats.getTotalAgentCount();
            prevTotalCardCount = prevDayStats.getTotalCardCount();
        }

        int morePrevTotalAgentCount = 0;
        int morePrevTotalCardCount = 0;
        if (morePrevDayStats != null) {
            morePrevTotalAgentCount = morePrevDayStats.getTotalAgentCount();
            morePrevTotalCardCount = morePrevDayStats.getTotalCardCount();
        }

        stats.setTotalAgent1Count(Optional.ofNullable(currentDayStats).map(ClsMarketEtcDayStats::getTotalAgent1Count).orElse(0));
        stats.setTotalAgent2Count(Optional.ofNullable(currentDayStats).map(ClsMarketEtcDayStats::getTotalAgent2Count).orElse(0));
        stats.setTotalAgent3Count(Optional.ofNullable(currentDayStats).map(ClsMarketEtcDayStats::getTotalAgent3Count).orElse(0));

        // 累计代理人数
        int currentTotalAgentCount = (Optional.ofNullable(currentDayStats).map(ClsMarketEtcDayStats::getTotalAgentCount).orElse(0));
        // 如果当前周期累计代理人数没有数据，则使用上一周期累计代理人数
        if (currentTotalAgentCount - prevTotalAgentCount > 0) {
            stats.setTotalAgentCount(currentTotalAgentCount);
        } else {
            stats.setTotalAgentCount(prevTotalAgentCount);
        }
        // 新增代理人数
        // 如果当前周期累计代理人数没有数据，则不要与上一周期累计代理人数进行减法运算
        if (stats.getTotalAgentCount() - prevTotalAgentCount > 0) {
            stats.setIncreaseAgentCount(stats.getTotalAgentCount() - prevTotalAgentCount);
        } else {
            stats.setIncreaseAgentCount(0);
        }
        // 累计发卡量
        int currentTotalCardCount = (Optional.ofNullable(currentDayStats).map(ClsMarketEtcDayStats::getTotalCardCount).orElse(0));
        // 如果当前周期累计发卡量没有数据，则使用上一周期累计发卡量
        if (currentTotalCardCount - prevTotalCardCount > 0) {
            stats.setTotalCardCount(currentTotalCardCount);
        } else {
            stats.setTotalCardCount(prevTotalCardCount);
        }
        // 新增发卡量
        // 如果当前周期累计发卡量没有数据，则不要与上一周期累计发卡量进行减法运算
        if (stats.getTotalCardCount() - prevTotalCardCount > 0) {
            stats.setIncreaseCardCount(stats.getTotalCardCount() - prevTotalCardCount);
        } else {
            stats.setIncreaseCardCount(0);
        }

        // 计算累计代理人数同比增长
        if (prevTotalAgentCount > 0) {
            stats.setTotalAgentCountRate(BigDecimal.valueOf(stats.getTotalAgentCount() * 1.00 / prevTotalAgentCount - 1).setScale(2, RoundingMode.HALF_UP));
        } else {
            if (stats.getTotalAgentCount() == 0) {
                stats.setTotalAgentCountRate(BigDecimal.valueOf(0.00));
            } else {
                stats.setTotalAgentCountRate(BigDecimal.valueOf(1.00));
            }
        }

        // 计算累计发卡量同比增长
        if (prevTotalCardCount > 0) {
            stats.setTotalCardCountRate(BigDecimal.valueOf(stats.getTotalCardCount() * 1.00 / prevTotalCardCount - 1).setScale(2, RoundingMode.HALF_UP));
        } else {
            if (stats.getTotalCardCount() == 0) {
                stats.setTotalCardCountRate(BigDecimal.valueOf(0.00));
            } else {
                stats.setTotalCardCountRate(BigDecimal.valueOf(1.00));
            }
        }

        // 计算新增代理人数同比增长
        if (prevTotalAgentCount - morePrevTotalAgentCount > 0) {
            stats.setIncreaseAgentCountRate(BigDecimal.valueOf(stats.getIncreaseAgentCount() * 1.00 / (prevTotalAgentCount - morePrevTotalAgentCount) - 1).setScale(2, RoundingMode.HALF_UP));
        } else {
            if (stats.getIncreaseAgentCount() == 0) {
                stats.setIncreaseAgentCountRate(BigDecimal.valueOf(0.00));
            } else {
                stats.setIncreaseAgentCountRate(BigDecimal.valueOf(1.00));
            }
        }

        // 计算新增发卡量同比增长
        if (prevTotalCardCount - morePrevTotalCardCount > 0) {
            stats.setIncreaseCardCountRate(BigDecimal.valueOf(stats.getIncreaseCardCount() * 1.00 / (prevTotalCardCount - morePrevTotalCardCount) - 1).setScale(2, RoundingMode.HALF_UP));
        } else {
            if (stats.getIncreaseCardCount() == 0) {
                stats.setIncreaseCardCountRate(BigDecimal.valueOf(0.00));
            } else {
                stats.setIncreaseCardCountRate(BigDecimal.valueOf(1.00));
            }
        }

        // 查询代理商排行统计
        List<ClsMarketEtcAgentDayStats> statsList = agentDayStatsDao.findAllByDateRange(fromDate, toDate, 1, 20);
        if (statsList != null && statsList.size() > 0) {
            List<StatsDto.AgentStats> agentRank = new ArrayList<>(statsList.size());
            for (ClsMarketEtcAgentDayStats agentDayStats : statsList) {
                ClsMarketEtcAgent agent = agentDao.findOne(agentDayStats.getAgentId());
                if (agent == null) {
                    continue;
                }
                StatsDto.AgentStats agentStats = new StatsDto.AgentStats();
                agentStats.setAgentName(agent.getRealName());
                agentStats.setCardCount(agentDayStats.getCardCount());
                agentStats.setPassageTimes(agentDayStats.getPassageTimes());
                agentStats.setPassageAmount(amountFormat(agentDayStats.getPassageAmount()));
                agentRank.add(agentStats);
            }
            dto.setAgentRank(agentRank);
        } else {
            dto.setAgentRank(new ArrayList<>(0));
        }
        return dto;
    }

    /**
     * 清理统计信息
     */
    public final void clearAll() {
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(DAY_STATS_LOCK_NAME);
        try {
            // 加锁
            rLock.lock(3000, TimeUnit.SECONDS);
            // 加锁成功
            log.info("清理日统计信息(已获取锁).");
            try {
                dayStatsDao.deleteAll();
            } catch (Exception e) {
                log.error("清理日统计信息异常", e);
            }
            // 释放锁
            rLock.unlock();
            log.info("清理日统计信息(已释放锁).");
        } catch (Exception e) {
            log.error("清理日统计信息执行异常", e);
        }

        // 获取分布式锁
        rLock = redissonClient.getLock(AGENT_DAY_STATS_LOCK_NAME);
        try {
            // 加锁
            rLock.lock(3000, TimeUnit.SECONDS);
            // 加锁成功
            log.info("清理代理商日统计信息(已获取锁).");
            try {
                agentDayStatsDao.deleteAll();
            } catch (Exception e) {
                log.error("清理代理商日统计信息异常", e);
            }
            // 释放锁
            rLock.unlock();
            log.info("清理代理商日统计信息(已释放锁).");
        } catch (Exception e) {
            log.error("清理代理商日统计信息执行异常", e);
        }
    }

    /**
     * 金额格式化
     * @param bigDecimal
     * @return
     */
    private String amountFormat(BigDecimal bigDecimal) {
        String formattedAmtStr = "";
        //DecimalFormat处理数字，3位加逗号分隔
        DecimalFormat df = new DecimalFormat("#,###.##");
        if (bigDecimal.compareTo(BigDecimal.valueOf(10000)) < 0) {
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            formattedAmtStr = df.format(bigDecimal);
        } else if (bigDecimal.compareTo(BigDecimal.valueOf(10000)) >= 0 && bigDecimal.compareTo(BigDecimal.valueOf(100000000)) < 0) {
            bigDecimal = bigDecimal.divide(new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_UP);
            formattedAmtStr = df.format(bigDecimal) + "万";
        } else {
            bigDecimal = bigDecimal.divide(new BigDecimal("100000000")).setScale(2, BigDecimal.ROUND_HALF_UP);
            formattedAmtStr = df.format(bigDecimal) + "亿";
        }
        return formattedAmtStr;
    }
}
