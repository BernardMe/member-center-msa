package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/7/29 12:48 下午.
 */
@Data
@ToString
@ApiModel("发货")
public class DeliverCommand {
    /**
     * 订单id
     */
    @NotNull(message = "请选择订单")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 卡列表
     */
    @ApiModelProperty("卡列表")
    private List<String> cardList;
    /**
     * 设备列表
     */
    @ApiModelProperty("设备列表")
    private List<String> deviceList;
}
