package com.zzjdyf.market.vo.command;

import com.zzjdyf.market.domain.entity.enums.GoodsType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author wangzhuo
 * Created on 20211108
 */
@Data
@ToString
@ApiModel("创建退货订单信息")
public class AddReturnOrderCommand {
    /**
     * 商品类型(卡|设备)
     */
    @ApiModelProperty("商品类型")
    private GoodsType goodsType;
    /**
     * 退货卡列表
     */
    @Size(min = 0, message = "请选择退货卡")
    @ApiModelProperty("卡列表")
    private List<String> cardList;
    /**
     * 退货设备列表
     */
    @Size(min = 0, message = "请选择退货设备")
    @ApiModelProperty("设备列表")
    private List<String> deviceList;
}
