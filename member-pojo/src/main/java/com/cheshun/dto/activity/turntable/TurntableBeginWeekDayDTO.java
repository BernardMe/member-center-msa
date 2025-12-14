package com.cheshun.dto.activity.turntable;

import java.util.ArrayList;
import java.util.Arrays;

public class TurntableBeginWeekDayDTO {
    private Integer turntableId;

    private Integer weekDay;

    public Integer getTurntableId() {
        return turntableId;
    }

    public void setTurntableId(Integer turntableId) {
        this.turntableId = turntableId;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", turntableId=").append(turntableId);
        sb.append(", weekDay=").append(weekDay);
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
        TurntableBeginWeekDayDTO other = (TurntableBeginWeekDayDTO) that;
        return (this.getTurntableId() == null ? other.getTurntableId() == null : this.getTurntableId().equals(other.getTurntableId()))
            && (this.getWeekDay() == null ? other.getWeekDay() == null : this.getWeekDay().equals(other.getWeekDay()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTurntableId() == null) ? 0 : getTurntableId().hashCode());
        result = prime * result + ((getWeekDay() == null) ? 0 : getWeekDay().hashCode());
        return result;
    }

    public enum Column {
        turntableId("turntable_id", "turntableId", "INTEGER", false),
        weekDay("week_day", "weekDay", "INTEGER", false);

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