package com.cheshun.entity.activity.en;

public enum TurntableTypeInfo {
    NONE("NONE", "默认"),
    INTEGRAL("INTEGRAL", "积分"),
    COST_RECORD("COST", "消费记录"),
    RECENT_COST("RECENT_COST", "最近消费");

    private final String code;
    private final String name;

    TurntableTypeInfo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getCodeByName(String name) {
        for (TurntableTypeInfo value : TurntableTypeInfo.values()) {
            if (value.getName().equals(name)) {
                return value.getCode();
            }
        }
        return null;
    }
}
