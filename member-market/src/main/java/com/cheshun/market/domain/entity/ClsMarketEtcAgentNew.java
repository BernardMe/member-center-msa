package com.cheshun.market.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 代理商信息
 *
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentNew extends ClsMarketEtcAgent {

    List<ClsMarketEtcAgentNew> clsMarketEtcAgentNewList;
}
