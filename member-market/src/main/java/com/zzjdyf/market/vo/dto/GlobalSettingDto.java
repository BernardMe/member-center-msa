package com.zzjdyf.market.vo.dto;

import com.zzjdyf.market.domain.entity.enums.GlobalSettingKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/8/20 5:49 下午.
 */
@Data
@ToString
@ApiModel("全局配置")
public class GlobalSettingDto {
    /**
     * 推广规则
     */
    @ApiModelProperty("推广规则")
    private List<PromoteRule> ruleList;

    @Data
    @ToString
    @ApiModel("推广规则")
    public static class PromoteRule {
        /**
         * 规则名
         */
        @ApiModelProperty("规则名")
        private GlobalSettingKey name;
        /**
         * 规则值
         */
        @ApiModelProperty("规则值")
        private int data;
        /**
         * 规则描述
         */
        @ApiModelProperty("规则描述")
        private String describe;
    }
}
