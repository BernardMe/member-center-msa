package com.zzjdyf.market.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author 阿隆
 * Created on 2021/7/28 1:45 下午.
 */
@Data
@ToString
@ApiModel("根据id查询参数")
public class IdQuery {
    /**
     * id
     */
    @NotNull(message = "id不允许为空")
    @ApiModelProperty("id")
    private Long id;
}
