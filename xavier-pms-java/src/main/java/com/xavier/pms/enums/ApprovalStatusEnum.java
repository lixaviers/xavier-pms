package com.xavier.pms.enums;

/**
 * @Comments: 审批状态枚举
 */
public enum ApprovalStatusEnum {
    UNPUBLISHED              ((byte) 0, "未发布"),
    NORMAL                   ((byte) 1, "正常"),
    DEACTIVATE               ((byte) 2, "停用"),
    ;

    private byte value;
    private String desc;

    ApprovalStatusEnum(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean exists(Byte status) {
        if (status == null) {
            return false;
        }
        byte s = status.byteValue();
        return exists(s);
    }

    public static boolean exists(byte s) {
        for (ApprovalStatusEnum element : ApprovalStatusEnum.values()) {
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
        for (ApprovalStatusEnum element : ApprovalStatusEnum.values()) {
            if (element.value == value.byteValue()) {
                return element.desc;
            }
        }
        return "";
    }
}
