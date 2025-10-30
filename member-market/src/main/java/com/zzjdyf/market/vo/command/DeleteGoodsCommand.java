package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@Data
@ToString
@ApiModel("删除商品")
public class DeleteGoodsCommand {
    /**
     * id
     */
    @NotNull(message = "请选择商品")
    @ApiModelProperty("id")
    private Long id;
}
