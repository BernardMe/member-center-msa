package com.cheshun.entity.order;

import com.cheshun.vo.store.StoreInfoVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MallAfterSalesDeceive implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<MallAfterSalesGoodsDeceive> mallAfterSalesGoodsDeceiveList;


    private String refundNumber;


    private Long refundTotalAmount;

    // 退款状态:0-待处理,1-审核通过,2-审核拒绝,3-退款中,4-退款成功,5-退款失败,6-已取消
    private Integer refundStatus;


    private String refundReason;

    private String refundEvidence;

    private String refundDesc;

    private String orderNumber;

    private String applyTime;


    private StoreInfoVO storeInfoVO;

    private String storeCode;
}
