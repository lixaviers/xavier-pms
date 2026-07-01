package com.xavier.pms.enums;

/**
 * 汇报类型枚举
 */
public enum ReportTypeEnum {

    DAILY   ((byte) 1, "日报"),
    WEEKLY  ((byte) 2, "周报"),
    MONTHLY ((byte) 3, "月报"),
    ;

    private final byte value;
    private final String desc;

    ReportTypeEnum(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte getValue() { return value; }
    public String getDesc() { return desc; }

    public static boolean exists(Byte value) {
        if (value == null) return false;
        for (ReportTypeEnum e : ReportTypeEnum.values()) {
            if (e.value == value) return true;
        }
        return false;
    }

    public static boolean exists(byte value) {
        for (ReportTypeEnum e : ReportTypeEnum.values()) {
            if (e.value == value) return true;
        }
        return false;
    }

    public boolean equal(Byte value) {
        return value != null && this.value == value;
    }

    public static String getDescByValue(Byte value) {
        if (value == null) return "";
        for (ReportTypeEnum e : ReportTypeEnum.values()) {
            if (e.value == value) return e.desc;
        }
        return "";
    }
}
