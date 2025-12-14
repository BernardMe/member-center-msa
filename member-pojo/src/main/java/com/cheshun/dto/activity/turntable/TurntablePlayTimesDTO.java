package com.cheshun.dto.activity.turntable;

import java.util.ArrayList;
import java.util.Arrays;

public class TurntablePlayTimesDTO {
    private String cardCode;

    private Integer turntableId;

    private Integer times;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Integer getTurntableId() {
        return turntableId;
    }

    public void setTurntableId(Integer turntableId) {
        this.turntableId = turntableId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cardCode=").append(cardCode);
        sb.append(", turntableId=").append(turntableId);
        sb.append(", times=").append(times);
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
        TurntablePlayTimesDTO other = (TurntablePlayTimesDTO) that;
        return (this.getCardCode() == null ? other.getCardCode() == null : this.getCardCode().equals(other.getCardCode()))
            && (this.getTurntableId() == null ? other.getTurntableId() == null : this.getTurntableId().equals(other.getTurntableId()))
            && (this.getTimes() == null ? other.getTimes() == null : this.getTimes().equals(other.getTimes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCardCode() == null) ? 0 : getCardCode().hashCode());
        result = prime * result + ((getTurntableId() == null) ? 0 : getTurntableId().hashCode());
        result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
        return result;
    }

    public enum Column {
        cardCode("card_code", "cardCode", "VARCHAR", false),
        turntableId("turntable_id", "turntableId", "INTEGER", false),
        times("times", "times", "INTEGER", false);

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