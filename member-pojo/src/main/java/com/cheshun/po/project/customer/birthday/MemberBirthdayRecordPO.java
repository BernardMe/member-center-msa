package com.cheshun.po.project.customer.birthday;

import lombok.Data;

import java.io.Serializable;

/**
 * 会员生日信息领券活动状态PO
 * @author wangzhuo
 */
@Data
public class MemberBirthdayRecordPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 会员姓名
     */
    private String memberName;
    /**
     * 会员卡号
     */
    private String memberCardNo;
    /**
     * 已选择的优惠券方案编码
     */
    private String choiceCouponNo;
    /**
     * 领取时间Str
     */
    private Byte received;
    /**
     * 更新生日时间Str
     */
    private String birthUpTimeStr;
    /**
     * 会员输入生日日期Str
     */
    private String inputBirthdayStr;
    /**
     * 领取时间Str
     */
    private String receiveDateStr;
    /**
     * 手机号
     */
    private String phone;

    @Override
    public String toString() {
        return "MemberBirthdayRecordPO{" +
                "activityId=" + activityId +
                ", memberName='" + memberName + '\'' +
                ", memberCardNo='" + memberCardNo + '\'' +
                ", choiceCouponNo='" + choiceCouponNo + '\'' +
                ", received=" + received +
                ", birthUpTimeStr='" + birthUpTimeStr + '\'' +
                ", inputBirthdayStr='" + inputBirthdayStr + '\'' +
                ", receiveDateStr='" + receiveDateStr + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

