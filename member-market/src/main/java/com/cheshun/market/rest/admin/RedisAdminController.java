package com.cheshun.market.rest.admin;

import com.cheshun.market.vo.command.RedisKeysCommand;
import com.cheshun.common.annotation.AdminApi;
import com.cheshun.common.annotation.NoToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clsapi/market/etc/admin/redis/notify")
@Api(tags = "后管系统-删除redis数据API")
@AllArgsConstructor
public class RedisAdminController {
    private final RedissonClient redissonClient;

    /**
     * 根据key值删除redis
     */
    @NoToken
    @AdminApi
    @PostMapping("deleteByRedis")
    @ApiOperation("根据key值删除redis")
    public void deleteByRedis(@RequestBody RedisKeysCommand redisKeysCommand) {
        for(String key:redisKeysCommand.getKeys()){
            redissonClient.getBucket(key).delete();
        }
    }
}
