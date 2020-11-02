package com.what.to.eat.server.enums;

import lombok.Getter;

public enum DataStatus {
    /**
     * 正常
     */
    NORMAL(2, "正常"),
    /**
     * 删除
     */
    DELETE(3, "删除"),

    HIDDEN(4, "隐藏 ");

    @Getter
    private int value;
    @Getter
    private String text;

    DataStatus(int val, String txt) {
        value = val;
        text = txt;
    }

}
