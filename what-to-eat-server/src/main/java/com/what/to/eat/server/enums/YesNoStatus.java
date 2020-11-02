package com.what.to.eat.server.enums;


/**
 * @author lixue
 */

public enum YesNoStatus {
    /**
     * YES。
     */
    YES("Y", "是"),

    /**
     * NO。
     */
    NO("N", "否");

    private String value;

    private String text;

    private YesNoStatus(String val, String txt) {
        value = val;
        text = txt;
    }

    /**
     * @return 当前枚举对象的值。
     */
    public String getValue() {
        return value;
    }

    /**
     * @return 当前状态的中文描述。
     */
    public String getText() {
        return text;
    }

    public static boolean isYes(String value) {
        return YES.getValue().equals(value);
    }

    public static boolean isNo(String value) {
        return NO.getValue().equals(value);
    }
}
