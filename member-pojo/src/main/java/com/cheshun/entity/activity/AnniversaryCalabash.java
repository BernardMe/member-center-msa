package com.cheshun.entity.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 周年庆葫芦活动表
 * @TableName anniversary_calabash_2025
 */
@TableName(value ="anniversary_calabash_2025")
@Data
public class AnniversaryCalabash {
    /**
     * 会员卡号
     */
    @TableId(value = "memberCardNo")
    private String membercardno;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Object gender;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private LocalDateTime birthday;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private LocalDateTime createtime;

    /**
     * 五人助力优惠券
     */
    @TableField(value = "coupon5")
    private String coupon5;

    /**
     * 十人助力优惠券
     */
    @TableField(value = "coupon10")
    private String coupon10;

    /**
     * 828礼包券
     */
    @TableField(value = "coupon828")
    private String coupon828;

    /**
     * 助力总数
     */
    @TableField(value = "helpCount")
    private Integer helpCount;

    /**
     * 葫芦助力1
     */
    @TableField(value = "calabash1")
    private String calabash1;

    /**
     * 葫芦助力2
     */
    @TableField(value = "calabash2")
    private String calabash2;

    /**
     * 葫芦助力3
     */
    @TableField(value = "calabash3")
    private String calabash3;

    /**
     * 葫芦助力4
     */
    @TableField(value = "calabash4")
    private String calabash4;

    /**
     * 葫芦助力5
     */
    @TableField(value = "calabash5")
    private String calabash5;

    /**
     * 葫芦助力6
     */
    @TableField(value = "calabash6")
    private String calabash6;

    /**
     * 葫芦助力7
     */
    @TableField(value = "calabash7")
    private String calabash7;

    /**
     * 葫芦助力8
     */
    @TableField(value = "calabash8")
    private String calabash8;

    /**
     * 葫芦助力9
     */
    @TableField(value = "calabash9")
    private String calabash9;

    /**
     * 葫芦助力10
     */
    @TableField(value = "calabash10")
    private String calabash10;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AnniversaryCalabash other = (AnniversaryCalabash) that;
        return (this.getMembercardno() == null ? other.getMembercardno() == null : this.getMembercardno().equals(other.getMembercardno()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getCoupon5() == null ? other.getCoupon5() == null : this.getCoupon5().equals(other.getCoupon5()))
            && (this.getCoupon10() == null ? other.getCoupon10() == null : this.getCoupon10().equals(other.getCoupon10()))
            && (this.getCoupon828() == null ? other.getCoupon828() == null : this.getCoupon828().equals(other.getCoupon828()))
            && (this.getHelpCount() == null ? other.getHelpCount() == null : this.getHelpCount().equals(other.getHelpCount()))
            && (this.getCalabash1() == null ? other.getCalabash1() == null : this.getCalabash1().equals(other.getCalabash1()))
            && (this.getCalabash2() == null ? other.getCalabash2() == null : this.getCalabash2().equals(other.getCalabash2()))
            && (this.getCalabash3() == null ? other.getCalabash3() == null : this.getCalabash3().equals(other.getCalabash3()))
            && (this.getCalabash4() == null ? other.getCalabash4() == null : this.getCalabash4().equals(other.getCalabash4()))
            && (this.getCalabash5() == null ? other.getCalabash5() == null : this.getCalabash5().equals(other.getCalabash5()))
            && (this.getCalabash6() == null ? other.getCalabash6() == null : this.getCalabash6().equals(other.getCalabash6()))
            && (this.getCalabash7() == null ? other.getCalabash7() == null : this.getCalabash7().equals(other.getCalabash7()))
            && (this.getCalabash8() == null ? other.getCalabash8() == null : this.getCalabash8().equals(other.getCalabash8()))
            && (this.getCalabash9() == null ? other.getCalabash9() == null : this.getCalabash9().equals(other.getCalabash9()))
            && (this.getCalabash10() == null ? other.getCalabash10() == null : this.getCalabash10().equals(other.getCalabash10()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMembercardno() == null) ? 0 : getMembercardno().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getCoupon5() == null) ? 0 : getCoupon5().hashCode());
        result = prime * result + ((getCoupon10() == null) ? 0 : getCoupon10().hashCode());
        result = prime * result + ((getCoupon828() == null) ? 0 : getCoupon828().hashCode());
        result = prime * result + ((getHelpCount() == null) ? 0 : getHelpCount().hashCode());
        result = prime * result + ((getCalabash1() == null) ? 0 : getCalabash1().hashCode());
        result = prime * result + ((getCalabash2() == null) ? 0 : getCalabash2().hashCode());
        result = prime * result + ((getCalabash3() == null) ? 0 : getCalabash3().hashCode());
        result = prime * result + ((getCalabash4() == null) ? 0 : getCalabash4().hashCode());
        result = prime * result + ((getCalabash5() == null) ? 0 : getCalabash5().hashCode());
        result = prime * result + ((getCalabash6() == null) ? 0 : getCalabash6().hashCode());
        result = prime * result + ((getCalabash7() == null) ? 0 : getCalabash7().hashCode());
        result = prime * result + ((getCalabash8() == null) ? 0 : getCalabash8().hashCode());
        result = prime * result + ((getCalabash9() == null) ? 0 : getCalabash9().hashCode());
        result = prime * result + ((getCalabash10() == null) ? 0 : getCalabash10().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", membercardno=").append(membercardno);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", createtime=").append(createtime);
        sb.append(", coupon5=").append(coupon5);
        sb.append(", coupon10=").append(coupon10);
        sb.append(", coupon828=").append(coupon828);
        sb.append(", helpCount=").append(helpCount);
        sb.append(", calabash1=").append(calabash1);
        sb.append(", calabash2=").append(calabash2);
        sb.append(", calabash3=").append(calabash3);
        sb.append(", calabash4=").append(calabash4);
        sb.append(", calabash5=").append(calabash5);
        sb.append(", calabash6=").append(calabash6);
        sb.append(", calabash7=").append(calabash7);
        sb.append(", calabash8=").append(calabash8);
        sb.append(", calabash9=").append(calabash9);
        sb.append(", calabash10=").append(calabash10);
        sb.append("]");
        return sb.toString();
    }
}