package com.cheshun.entity.member.check;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class VipCheckUser {
    private Integer id;

    private String vipId;

    private String vipCardNo;

    private LocalDateTime createDateTime;

    private String main;

    private String secondary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }

    public String getVipCardNo() {
        return vipCardNo;
    }

    public void setVipCardNo(String vipCardNo) {
        this.vipCardNo = vipCardNo;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", vipId=").append(vipId);
        sb.append(", vipCardNo=").append(vipCardNo);
        sb.append(", createDateTime=").append(createDateTime);
        sb.append(", main=").append(main);
        sb.append(", secondary=").append(secondary);
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
        VipCheckUser other = (VipCheckUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVipId() == null ? other.getVipId() == null : this.getVipId().equals(other.getVipId()))
            && (this.getVipCardNo() == null ? other.getVipCardNo() == null : this.getVipCardNo().equals(other.getVipCardNo()))
            && (this.getCreateDateTime() == null ? other.getCreateDateTime() == null : this.getCreateDateTime().equals(other.getCreateDateTime()))
            && (this.getMain() == null ? other.getMain() == null : this.getMain().equals(other.getMain()))
            && (this.getSecondary() == null ? other.getSecondary() == null : this.getSecondary().equals(other.getSecondary()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVipId() == null) ? 0 : getVipId().hashCode());
        result = prime * result + ((getVipCardNo() == null) ? 0 : getVipCardNo().hashCode());
        result = prime * result + ((getCreateDateTime() == null) ? 0 : getCreateDateTime().hashCode());
        result = prime * result + ((getMain() == null) ? 0 : getMain().hashCode());
        result = prime * result + ((getSecondary() == null) ? 0 : getSecondary().hashCode());
        return result;
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        vipId("vip_id", "vipId", "VARCHAR", false),
        vipCardNo("vip_card_no", "vipCardNo", "VARCHAR", false),
        createDateTime("create_date_time", "createDateTime", "TIMESTAMP", false),
        main("main", "main", "VARCHAR", false),
        secondary("secondary", "secondary", "VARCHAR", false);

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