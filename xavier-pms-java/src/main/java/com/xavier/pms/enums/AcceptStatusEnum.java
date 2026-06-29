package com.xavier.pms.enums;

/**
 * 参与状态枚举
 */
public enum AcceptStatusEnum {

    PENDING   ((byte) 0, "待确认"),
    ACCEPTED  ((byte) 1, "已接受"),
    REJECTED  ((byte) 2, "已拒绝"),
    ;

    private final byte value;
    private final String desc;

    AcceptStatusEnum(byte value, String desc) {
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
        for (AcceptStatusEnum element : AcceptStatusEnum.values()) {
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
        for (AcceptStatusEnum element : AcceptStatusEnum.values()) {
            if (element.value == value.byteValue()) {
                return element.desc;
            }
        }
        return "";
    }
}
