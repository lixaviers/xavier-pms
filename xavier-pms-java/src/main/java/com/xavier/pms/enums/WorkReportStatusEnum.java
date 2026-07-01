package com.xavier.pms.enums;

/**
 * 汇报状态枚举
 */
public enum WorkReportStatusEnum {

    DRAFT     ((byte) 1, "草稿"),
    SUBMITTED ((byte) 2, "已提交"),
    ;

    private final byte value;
    private final String desc;

    WorkReportStatusEnum(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte getValue() { return value; }
    public String getDesc() { return desc; }

    public static boolean exists(Byte value) {
        if (value == null) return false;
        for (WorkReportStatusEnum e : WorkReportStatusEnum.values()) {
            if (e.value == value) return true;
        }
        return false;
    }

    public static boolean exists(byte value) {
        for (WorkReportStatusEnum e : WorkReportStatusEnum.values()) {
            if (e.value == value) return true;
        }
        return false;
    }

    public boolean equal(Byte value) {
        return value != null && this.value == value;
    }

    public static String getDescByValue(Byte value) {
        if (value == null) return "";
        for (WorkReportStatusEnum e : WorkReportStatusEnum.values()) {
            if (e.value == value) return e.desc;
        }
        return "";
    }
}
