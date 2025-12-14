package com.cheshun.entity.project.customer.survey;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SurveyCouponRecord implements Serializable {
    private Integer id;

    private Integer paperId;

    private String memberCardNo;

    private String memberPhone;

    private Byte couponReceived;

    private LocalDateTime createDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo == null ? null : memberCardNo.trim();
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone == null ? null : memberPhone.trim();
    }

    public Byte getCouponReceived() {
        return couponReceived;
    }

    public void setCouponReceived(Byte couponReceived) {
        this.couponReceived = couponReceived;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paperId=").append(paperId);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", memberPhone=").append(memberPhone);
        sb.append(", couponReceived=").append(couponReceived);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}