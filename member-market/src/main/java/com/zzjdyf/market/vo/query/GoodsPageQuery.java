package com.zzjdyf.market.vo.query;

import com.zzjdyf.market.domain.entity.enums.PublishStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 阿隆
 * Created on 2021/7/20 9:20 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询商品参数")
public class GoodsPageQuery extends PageQuery {
    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    private String brand;
    /**
     * 型号
     */
    @ApiModelProperty("型号")
    private String model;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private PublishStatus status;
}
