package com.xavier.pms.enums;

/**
 * @Comments: 离职类型枚举
 */
public enum ResignationTypeEnum {
    VOLUNTARY          ((byte) 1, "主动离职"),
    DISMISSAL          ((byte) 2, "辞退"),
    CONTRACT_EXPIRY    ((byte) 3, "合同到期"),
    OTHER              ((byte) 4, "其他"),
    ;

    private final byte value;
    private final String desc;

    ResignationTypeEnum(byte value, String desc) {
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
        for (ResignationTypeEnum element : ResignationTypeEnum.values()) {
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
        for (ResignationTypeEnum element : ResignationTypeEnum.values()) {
            if (element.value == value.byteValue()) {
                return element.desc;
            }
        }
        return "";
    }
}
