package com.cheshun.entity.store;


import com.cheshun.dto.employee.QwEmployeesDTO;

import java.io.Serializable;

public class StoreEmployeePO extends QwEmployeesDTO implements Serializable {

    /**
     * 企微二维码
     */
    private String qrCode;


    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
