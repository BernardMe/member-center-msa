package com.cheshun.entity.store;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GlCustomDTO implements Serializable {
    private String cMdfq;

    private String cDq;

    private String ds;

    private String cMdfq1;

    private String tjbh;

    private String mc;

    private LocalDateTime cKykdrq;

    private String cMdfl;

    private String tjTag;

    private String cDownloadaddress;

    private BigDecimal cMdlx;

    private String spjxflz;

    private String subsellpricetype;

    private String cMdsp;

    private static final long serialVersionUID = 1L;

    public String getcMdfq() {
        return cMdfq;
    }

    public void setcMdfq(String cMdfq) {
        this.cMdfq = cMdfq == null ? null : cMdfq.trim();
    }

    public String getcDq() {
        return cDq;
    }

    public void setcDq(String cDq) {
        this.cDq = cDq == null ? null : cDq.trim();
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds == null ? null : ds.trim();
    }

    public String getcMdfq1() {
        return cMdfq1;
    }

    public void setcMdfq1(String cMdfq1) {
        this.cMdfq1 = cMdfq1 == null ? null : cMdfq1.trim();
    }

    public String getTjbh() {
        return tjbh;
    }

    public void setTjbh(String tjbh) {
        this.tjbh = tjbh == null ? null : tjbh.trim();
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    public LocalDateTime getcKykdrq() {
        return cKykdrq;
    }

    public void setcKykdrq(LocalDateTime cKykdrq) {
        this.cKykdrq = cKykdrq;
    }

    public String getcMdfl() {
        return cMdfl;
    }

    public void setcMdfl(String cMdfl) {
        this.cMdfl = cMdfl == null ? null : cMdfl.trim();
    }

    public String getTjTag() {
        return tjTag;
    }

    public void setTjTag(String tjTag) {
        this.tjTag = tjTag == null ? null : tjTag.trim();
    }

    public String getcDownloadaddress() {
        return cDownloadaddress;
    }

    public void setcDownloadaddress(String cDownloadaddress) {
        this.cDownloadaddress = cDownloadaddress == null ? null : cDownloadaddress.trim();
    }

    public BigDecimal getcMdlx() {
        return cMdlx;
    }

    public void setcMdlx(BigDecimal cMdlx) {
        this.cMdlx = cMdlx;
    }

    public String getSpjxflz() {
        return spjxflz;
    }

    public void setSpjxflz(String spjxflz) {
        this.spjxflz = spjxflz == null ? null : spjxflz.trim();
    }

    public String getSubsellpricetype() {
        return subsellpricetype;
    }

    public void setSubsellpricetype(String subsellpricetype) {
        this.subsellpricetype = subsellpricetype == null ? null : subsellpricetype.trim();
    }

    public String getcMdsp() {
        return cMdsp;
    }

    public void setcMdsp(String cMdsp) {
        this.cMdsp = cMdsp == null ? null : cMdsp.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cMdfq=").append(cMdfq);
        sb.append(", cDq=").append(cDq);
        sb.append(", ds=").append(ds);
        sb.append(", cMdfq1=").append(cMdfq1);
        sb.append(", tjbh=").append(tjbh);
        sb.append(", mc=").append(mc);
        sb.append(", cKykdrq=").append(cKykdrq);
        sb.append(", cMdfl=").append(cMdfl);
        sb.append(", tjTag=").append(tjTag);
        sb.append(", cDownloadaddress=").append(cDownloadaddress);
        sb.append(", cMdlx=").append(cMdlx);
        sb.append(", spjxflz=").append(spjxflz);
        sb.append(", subsellpricetype=").append(subsellpricetype);
        sb.append(", cMdsp=").append(cMdsp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}