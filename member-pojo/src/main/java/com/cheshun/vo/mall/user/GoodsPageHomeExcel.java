package com.cheshun.vo.mall.user;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GoodsPageHomeExcel {

    @ExcelProperty(value = "商品编号(货号)")
    private String goodsCode;

    @ExcelProperty(value = "品名(可不填)")
    private String productName;

    @ExcelProperty(value = "排序(必填：越低越往前)")
    private Integer goodsSort;

    @ExcelProperty(value = "商品(id)")
    private String goodsId;
}
