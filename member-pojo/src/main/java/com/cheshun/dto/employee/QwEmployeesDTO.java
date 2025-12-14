package com.cheshun.dto.employee;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QwEmployeesDTO implements Serializable {
    private String code;

    private String guid;

    private String name;

    private String mobilephone;

    private String departcode;

    private String dingid;

    private String departdingid;

    private String state;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private String updateuser;

    private String dingzw;

    private Long comparetime;

    private Double isleaf;

    private String englishname;

    private String othername;

    private Long frank;

    private String directSupervisor;

    private String otherdepart;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getDepartcode() {
        return departcode;
    }

    public void setDepartcode(String departcode) {
        this.departcode = departcode == null ? null : departcode.trim();
    }

    public String getDingid() {
        return dingid;
    }

    public void setDingid(String dingid) {
        this.dingid = dingid == null ? null : dingid.trim();
    }

    public String getDepartdingid() {
        return departdingid;
    }

    public void setDepartdingid(String departdingid) {
        this.departdingid = departdingid == null ? null : departdingid.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public String getDingzw() {
        return dingzw;
    }

    public void setDingzw(String dingzw) {
        this.dingzw = dingzw == null ? null : dingzw.trim();
    }

    public Long getComparetime() {
        return comparetime;
    }

    public void setComparetime(Long comparetime) {
        this.comparetime = comparetime;
    }

    public Double getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Double isleaf) {
        this.isleaf = isleaf;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname == null ? null : englishname.trim();
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername == null ? null : othername.trim();
    }

    public Long getFrank() {
        return frank;
    }

    public void setFrank(Long frank) {
        this.frank = frank;
    }

    public String getDirectSupervisor() {
        return directSupervisor;
    }

    public void setDirectSupervisor(String directSupervisor) {
        this.directSupervisor = directSupervisor == null ? null : directSupervisor.trim();
    }

    public String getOtherdepart() {
        return otherdepart;
    }

    public void setOtherdepart(String otherdepart) {
        this.otherdepart = otherdepart == null ? null : otherdepart.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", guid=").append(guid);
        sb.append(", name=").append(name);
        sb.append(", mobilephone=").append(mobilephone);
        sb.append(", departcode=").append(departcode);
        sb.append(", dingid=").append(dingid);
        sb.append(", departdingid=").append(departdingid);
        sb.append(", state=").append(state);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", updateuser=").append(updateuser);
        sb.append(", dingzw=").append(dingzw);
        sb.append(", comparetime=").append(comparetime);
        sb.append(", isleaf=").append(isleaf);
        sb.append(", englishname=").append(englishname);
        sb.append(", othername=").append(othername);
        sb.append(", frank=").append(frank);
        sb.append(", directSupervisor=").append(directSupervisor);
        sb.append(", otherdepart=").append(otherdepart);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}