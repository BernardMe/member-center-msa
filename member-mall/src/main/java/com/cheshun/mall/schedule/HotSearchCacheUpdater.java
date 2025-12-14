package com.cheshun.mall.schedule;

import com.cheshun.common.result.Result;
import com.cheshun.mall.service.HotSearchService;
import com.cheshun.mall.vo.dto.HotSearchPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotSearchCacheUpdater {

    @Autowired
    private HotSearchService hotSearchService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(fixedRate = 60000) // 每分钟更新一次
    public void updateHotSearches() {
        //List<HotSearchPO> hotSearches = hotSearchService.getHotSearches();
        Result<List<HotSearchPO>> result = hotSearchService.getTopSearches();
        List<HotSearchPO> hotSearchPOList = result.getData();
        /*String hotSearchesJson = hotSearchPOList.stream()
                .map(searchPO -> String.format("%s:%s", searchPO.getWords(), searchPO.getScore()) ) // 将热搜词及热度转换为字符串
                .collect(Collectors.joining(",")); // 用逗号分隔*/
        redisTemplate.opsForValue().set("top_searches", hotSearchPOList); // 将top_searches结果存入 Redis
    }
}
