package com.cheshun.entity.member.check;

import java.util.ArrayList;
import java.util.Arrays;

public class VipCheckQuestion {
    private Integer id;

    private String title;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String optionE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getOptionE() {
        return optionE;
    }

    public void setOptionE(String optionE) {
        this.optionE = optionE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", optionA=").append(optionA);
        sb.append(", optionB=").append(optionB);
        sb.append(", optionC=").append(optionC);
        sb.append(", optionD=").append(optionD);
        sb.append(", optionE=").append(optionE);
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
        VipCheckQuestion other = (VipCheckQuestion) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getOptionA() == null ? other.getOptionA() == null : this.getOptionA().equals(other.getOptionA()))
            && (this.getOptionB() == null ? other.getOptionB() == null : this.getOptionB().equals(other.getOptionB()))
            && (this.getOptionC() == null ? other.getOptionC() == null : this.getOptionC().equals(other.getOptionC()))
            && (this.getOptionD() == null ? other.getOptionD() == null : this.getOptionD().equals(other.getOptionD()))
            && (this.getOptionE() == null ? other.getOptionE() == null : this.getOptionE().equals(other.getOptionE()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getOptionA() == null) ? 0 : getOptionA().hashCode());
        result = prime * result + ((getOptionB() == null) ? 0 : getOptionB().hashCode());
        result = prime * result + ((getOptionC() == null) ? 0 : getOptionC().hashCode());
        result = prime * result + ((getOptionD() == null) ? 0 : getOptionD().hashCode());
        result = prime * result + ((getOptionE() == null) ? 0 : getOptionE().hashCode());
        return result;
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        title("title", "title", "VARCHAR", false),
        optionA("option_a", "optionA", "VARCHAR", false),
        optionB("option_b", "optionB", "VARCHAR", false),
        optionC("option_c", "optionC", "VARCHAR", false),
        optionD("option_d", "optionD", "VARCHAR", false),
        optionE("option_e", "optionE", "VARCHAR", false);

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