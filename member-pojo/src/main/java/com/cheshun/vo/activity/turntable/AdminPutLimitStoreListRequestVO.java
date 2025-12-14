package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加限制门店-请求信息")
public class AdminPutLimitStoreListRequestVO {

    @ApiModelProperty(value = "转盘ID")
    private Integer turntableId;

    @ApiModelProperty(value = "门店批次列表")
    private List<Batch> storeCodeBatchList;

    @Data
    public static class Batch {
        private String staTime;
        private List<String> storeCodeList;
    }
}
