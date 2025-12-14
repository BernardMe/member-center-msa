package com.cheshun.dto.order;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class GoodsCategoryExcelDTO {
    @ExcelProperty("商品货号")
    private String goodsCode;

    @ExcelProperty("商品名称")
    private String productName;

    @ExcelProperty("药品分类")
    private String drugCategory;

    @ExcelProperty("状态 1-上架 0-下架")
    private Integer status;

    @ExcelProperty("一级分类名称")
    private String parentCategoryName;

    @ExcelProperty("二级分类名称")
    private String categoryName;
}
