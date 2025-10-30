package com.zzjdyf.market.rest.admin;

import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.common.annotation.NoToken;
import com.zzjdyf.market.vo.command.RedisKeysCommand;
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
