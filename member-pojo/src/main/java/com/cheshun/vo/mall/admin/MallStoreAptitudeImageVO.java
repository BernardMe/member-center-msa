package com.cheshun.vo.mall.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("门店资质图片表")
public class MallStoreAptitudeImageVO {

    @ApiModelProperty("门店编号")
    private String storeCode;

    @ApiModelProperty("门店名称")
    private String storeName;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("类型编号")
    private String typeCode;

    @ApiModelProperty("类型描述")
    private String typeDescription;

    @ApiModelProperty("排序序号")
    private Integer orderNum;

    @ApiModelProperty("过期时间")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireAt;

}
