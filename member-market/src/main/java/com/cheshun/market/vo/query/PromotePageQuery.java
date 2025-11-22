package com.cheshun.market.vo.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 阿隆
 * Created on 2021/8/4 3:08 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询分润记录参数")
public class PromotePageQuery extends PageQuery {
    /**
     * 查询类型(0:查询已激活分佣奖励,1:查询已消费分佣奖励)
     */
    @ApiModelProperty("查询类型(0:查询已激活分佣奖励,1:查询已消费分佣奖励)")
    private QueryType type = QueryType.ACTIVATED;

    /**
     * 查询类型
     */
    public enum QueryType {
        /**
         * 查询已激活分佣奖励
         */
        ACTIVATED(0),
        /**
         * 查询已消费分佣奖励
         */
        CONSUMED(1);

        @JsonValue
        private final Integer value;

        QueryType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        @JsonCreator
        public static QueryType valueByCode(Integer code) {
            for (QueryType item : values()) {
                if (item.value.equals(code)) {
                    return item;
                }
            }
            return null;
        }
    }
}
