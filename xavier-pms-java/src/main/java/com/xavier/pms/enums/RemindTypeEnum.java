package com.xavier.pms.enums;

/**
 * 提醒方式枚举
 */
public enum RemindTypeEnum {

    NONE          ((byte) 0, "不提醒"),
    BEFORE_5MIN   ((byte) 1, "提前5分钟"),
    BEFORE_15MIN  ((byte) 2, "提前15分钟"),
    BEFORE_30MIN  ((byte) 3, "提前30分钟"),
    BEFORE_1HOUR  ((byte) 4, "提前1小时"),
    BEFORE_1DAY   ((byte) 5, "提前1天"),
    ;

    private final byte value;
    private final String desc;

    RemindTypeEnum(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean exists(Byte type) {
        if (type == null) {
            return false;
        }
        byte s = type.byteValue();
        return exists(s);
    }

    public static boolean exists(byte s) {
        for (RemindTypeEnum element : RemindTypeEnum.values()) {
            if (element.value == s) {
                return true;
            }
        }
        return false;
    }

    public boolean equal(Byte val) {
        return val == null ? false : val.byteValue() == this.value;
    }

    public static String getDescByValue(Byte value) {
        if (value == null) {
            return "";
        }
        for (RemindTypeEnum element : RemindTypeEnum.values()) {
            if (element.value == value.byteValue()) {
                return element.desc;
            }
        }
        return "";
    }
}
