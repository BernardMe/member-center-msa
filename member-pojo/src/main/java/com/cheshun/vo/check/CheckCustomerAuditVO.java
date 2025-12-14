package com.cheshun.vo.check;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "运营端-审核会员登记分享记录参数")
@Data
public class CheckCustomerAuditVO implements Serializable {

    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 会员登记id
     */
    private Integer checkId;
    /**
     * 审核状态(0:无需审核, 1:审核成功, 2:审核失败, 3:OCR识别中, 4:OCR识别成功, 5:OCR识别失败)
     */
    private Byte auditStatus;

}
