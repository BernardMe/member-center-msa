package com.cheshun.dto.activity.turntable;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class TurntablePlayRecordDTO {
    private Integer id;

    private Integer turntableId;

    private String turntableName;

    private String userCardNo;

    private String userPhone;

    private String consigneeName;

    private String consigneePhone;

    private String consigneeAddress;

    private Integer prizeId;

    private String prizeName;

    private String prizeType;

    private String prizePicture;

    private String finishTag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime finishTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTurntableId() {
        return turntableId;
    }

    public void setTurntableId(Integer turntableId) {
        this.turntableId = turntableId;
    }

    public String getTurntableName() {
        return turntableName;
    }

    public void setTurntableName(String turntableName) {
        this.turntableName = turntableName;
    }

    public String getUserCardNo() {
        return userCardNo;
    }

    public void setUserCardNo(String userCardNo) {
        this.userCardNo = userCardNo;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public String getPrizePicture() {
        return prizePicture;
    }

    public void setPrizePicture(String prizePicture) {
        this.prizePicture = prizePicture;
    }

    public String getFinishTag() {
        return finishTag;
    }

    public void setFinishTag(String finishTag) {
        this.finishTag = finishTag;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", turntableId=").append(turntableId);
        sb.append(", turntableName=").append(turntableName);
        sb.append(", userCardNo=").append(userCardNo);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", consigneeName=").append(consigneeName);
        sb.append(", consigneePhone=").append(consigneePhone);
        sb.append(", consigneeAddress=").append(consigneeAddress);
        sb.append(", prizeId=").append(prizeId);
        sb.append(", prizeName=").append(prizeName);
        sb.append(", prizeType=").append(prizeType);
        sb.append(", prizePicture=").append(prizePicture);
        sb.append(", finishTag=").append(finishTag);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

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
        TurntablePlayRecordDTO other = (TurntablePlayRecordDTO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTurntableId() == null ? other.getTurntableId() == null : this.getTurntableId().equals(other.getTurntableId()))
            && (this.getTurntableName() == null ? other.getTurntableName() == null : this.getTurntableName().equals(other.getTurntableName()))
            && (this.getUserCardNo() == null ? other.getUserCardNo() == null : this.getUserCardNo().equals(other.getUserCardNo()))
            && (this.getUserPhone() == null ? other.getUserPhone() == null : this.getUserPhone().equals(other.getUserPhone()))
            && (this.getConsigneeName() == null ? other.getConsigneeName() == null : this.getConsigneeName().equals(other.getConsigneeName()))
            && (this.getConsigneePhone() == null ? other.getConsigneePhone() == null : this.getConsigneePhone().equals(other.getConsigneePhone()))
            && (this.getConsigneeAddress() == null ? other.getConsigneeAddress() == null : this.getConsigneeAddress().equals(other.getConsigneeAddress()))
            && (this.getPrizeId() == null ? other.getPrizeId() == null : this.getPrizeId().equals(other.getPrizeId()))
            && (this.getPrizeName() == null ? other.getPrizeName() == null : this.getPrizeName().equals(other.getPrizeName()))
            && (this.getPrizeType() == null ? other.getPrizeType() == null : this.getPrizeType().equals(other.getPrizeType()))
            && (this.getPrizePicture() == null ? other.getPrizePicture() == null : this.getPrizePicture().equals(other.getPrizePicture()))
            && (this.getFinishTag() == null ? other.getFinishTag() == null : this.getFinishTag().equals(other.getFinishTag()))
            && (this.getFinishTime() == null ? other.getFinishTime() == null : this.getFinishTime().equals(other.getFinishTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTurntableId() == null) ? 0 : getTurntableId().hashCode());
        result = prime * result + ((getTurntableName() == null) ? 0 : getTurntableName().hashCode());
        result = prime * result + ((getUserCardNo() == null) ? 0 : getUserCardNo().hashCode());
        result = prime * result + ((getUserPhone() == null) ? 0 : getUserPhone().hashCode());
        result = prime * result + ((getConsigneeName() == null) ? 0 : getConsigneeName().hashCode());
        result = prime * result + ((getConsigneePhone() == null) ? 0 : getConsigneePhone().hashCode());
        result = prime * result + ((getConsigneeAddress() == null) ? 0 : getConsigneeAddress().hashCode());
        result = prime * result + ((getPrizeId() == null) ? 0 : getPrizeId().hashCode());
        result = prime * result + ((getPrizeName() == null) ? 0 : getPrizeName().hashCode());
        result = prime * result + ((getPrizeType() == null) ? 0 : getPrizeType().hashCode());
        result = prime * result + ((getPrizePicture() == null) ? 0 : getPrizePicture().hashCode());
        result = prime * result + ((getFinishTag() == null) ? 0 : getFinishTag().hashCode());
        result = prime * result + ((getFinishTime() == null) ? 0 : getFinishTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        turntableId("turntable_id", "turntableId", "INTEGER", false),
        turntableName("turntable_name", "turntableName", "VARCHAR", false),
        userCardNo("user_card_no", "userCardNo", "VARCHAR", false),
        userPhone("user_phone", "userPhone", "VARCHAR", false),
        consigneeName("consignee_name", "consigneeName", "VARCHAR", false),
        consigneePhone("consignee_phone", "consigneePhone", "VARCHAR", false),
        consigneeAddress("consignee_address", "consigneeAddress", "VARCHAR", false),
        prizeId("prize_id", "prizeId", "INTEGER", false),
        prizeName("prize_name", "prizeName", "VARCHAR", false),
        prizeType("prize_type", "prizeType", "VARCHAR", false),
        prizePicture("prize_picture", "prizePicture", "VARCHAR", false),
        finishTag("finish_tag", "finishTag", "VARCHAR", false),
        finishTime("finish_time", "finishTime", "TIMESTAMP", false),
        createTime("create_time", "createTime", "TIMESTAMP", false);

        private static final String BEGINNING_DELIMITER = "`";

        private static final String ENDING_DELIMITER = "`";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}