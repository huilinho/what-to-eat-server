package com.what.to.eat.server.enums;

import lombok.Getter;

public enum AuditStatus {
    NOT_APPLY(0, "未申请"),
    /**
     * 未审核
     */
    NOT_AUDIT(1, "未审核"),

    AUDIT_PASS(2, "通过"),

    AUDIT_REJECT(3, "拒绝");

    @Getter
    private int value;

    private String text;

    AuditStatus(int val, String txt) {
        this.value = val;
        this.text = txt;
    }
}

