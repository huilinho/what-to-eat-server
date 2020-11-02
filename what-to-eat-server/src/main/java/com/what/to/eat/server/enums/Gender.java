package com.what.to.eat.server.enums;

import lombok.Getter;

public enum Gender {
    /**
     * 未知
     */
    UNKNOWN(0, "未知"),
    /**
     * 男
     */
    MALE(1, "男"),
    /**
     * 女
     */
    FEMALE(2, "女");

    @Getter
    private int value;

    @Getter
    private String text;

    Gender(int val, String txt) {
        this.value = val;
        this.text = txt;
    }

}
