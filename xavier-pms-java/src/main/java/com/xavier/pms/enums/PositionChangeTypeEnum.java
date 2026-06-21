package com.xavier.pms.enums;

/**
 * 变动类型枚举
 */
public enum PositionChangeTypeEnum {
    POST_CHANGE        ((byte) 1, "职位变动"),
    TITLE_CHANGE       ((byte) 2, "职称变动"),
    ;

    private final byte value;
    private final String desc;

    PositionChangeTypeEnum(byte value, String desc) {
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
        for (PositionChangeTypeEnum element : PositionChangeTypeEnum.values()) {
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
        for (PositionChangeTypeEnum element : PositionChangeTypeEnum.values()) {
            if (element.value == value.byteValue()) {
                return element.desc;
            }
        }
        return "";
    }
}
