package com.cheshun.po.user.plantation.sign;

import java.io.Serializable;

public class SignInPOJO implements Serializable {
    /**
     * 表示签到的天数
     */
    private Integer day;

    /**
     * 天数对应的水滴数
     */
    private Integer drip;

    /**
     * 1表示已经签到, 0表示未签到
     */
    private Integer flag;

    public SignInPOJO(Integer day, Integer flag) {
        this.day = day;
        this.flag = flag;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getDrip() {
        return drip;
    }

    public void setDrip(Integer drip) {
        this.drip = drip;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}