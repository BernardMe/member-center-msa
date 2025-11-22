package com.cheshun.market.domain.entity;

import com.cheshun.market.domain.entity.enums.TradeType;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 阿隆
 * Created on 2021/7/29 6:29 下午.
 */
@Data
@Document(indexName = "cls_market_etc", type = "agent_order")
@ToString
public class ClsMarketEtcEsTrade {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 交易id
     */
    @Field(type = FieldType.Long, fielddata = true)
    private long tradeId;
    /**
     * 代理商id
     */
    @Field(type = FieldType.Long, fielddata = true)
    private long agentId;
    /**
     * 代理商手机号
     */
    @Field(type = FieldType.Keyword)
    private String phone;
    /**
     * 代理商真实姓名
     */
    @Field(type = FieldType.Keyword)
    private String realName;
    /**
     * 代理商角色
     */
    @Field(type = FieldType.Long, fielddata = true)
    private int role;
    /**
     * 交易类型
     */
    @Field(type = FieldType.Keyword)
    private TradeType type;
    /**
     * 更新时间
     */
    @Field(type = FieldType.Long, fielddata = true)
    private long updateTime;
    /**
     * 创建时间
     */
    @Field(type = FieldType.Long, fielddata = true)
    private long createTime;
}
