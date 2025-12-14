package com.cheshun.po.project.customer.teacher;

import java.io.Serializable;

/**
 * 教资证信息PO
 * @author wangzhuo
 */
public class TeacherCertInfoPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 教师姓名
     */
    private String teacherName;
    /**
     * 教资证编码
     */
    private String certificateNum;
    /**
     * 教资证图片url
     */
    private String certPicUrl;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getCertPicUrl() {
        return certPicUrl;
    }

    public void setCertPicUrl(String certPicUrl) {
        this.certPicUrl = certPicUrl;
    }

    @Override
    public String toString() {
        return "TeacherCertInfoPO{" +
                "teacherName='" + teacherName + '\'' +
                ", certificateNum='" + certificateNum + '\'' +
                ", certPicUrl='" + certPicUrl + '\'' +
                '}';
    }
}

