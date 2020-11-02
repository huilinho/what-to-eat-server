package com.what.to.eat.server.enums;

import lombok.Getter;

/**
 * @author lixue
 */

public enum PlanType {
    /**
     * 调机
     */
    TRANSFER(1, "调机"),
    /**
     * 公务
     */
    BUSINESS(2, "公务"),
    /**
     * AOG: airplane on ground
     */
    AOG(3, "AOG");

    @Getter
    private int value;

    private String text;

    PlanType(int val, String txt) {
        this.value = val;
        this.text = txt;
    }
}
