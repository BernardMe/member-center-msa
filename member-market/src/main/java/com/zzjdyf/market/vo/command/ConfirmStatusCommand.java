package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangzhuo
 * Created on 20210802
 */
@Data
@ToString
@ApiModel("根据id确认状态参数")
public class ConfirmStatusCommand {
    /**
     * id
     */
    @NotNull(message = "ids不允许为空")
    @ApiModelProperty("ids")
    private List<Long> ids;
    /**
     * 类型(1:卡 2:设备)
     */
    @ApiModelProperty("类型(1:卡 2:设备)")
    private Integer type;
}
