package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@ApiModel("根据key删除redis的值")
public class RedisKeysCommand {

    @ApiModelProperty("keys")
    private List<String> keys;
}
