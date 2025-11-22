package com.cheshun.market.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Data
@ToString
@ApiModel("分页查询参数")
public class PageQuery {
    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private int pageNum = 1;
    /**
     * 每页查询数量
     */
    @ApiModelProperty("每页查询数量")
    private int pageSize = 10;
}
