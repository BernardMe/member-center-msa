package com.cheshun.market.vo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WechatLoginDto extends BaseDto{

   public Boolean exist;

   public String openId;

   public AgentAppDto agentAppDto;

}
