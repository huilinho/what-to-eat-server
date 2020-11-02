package net.scode.commons.constant;

import net.scode.commons.core.IdNameEnum;

/**
 * 数据状态枚举
 *
 * @author tanghuang 2020年02月23日
 */
public enum DataStatus implements IdNameEnum {

    /**
     * 状态类型定义
     */
    WAIT_AUDIT(1, "待审核"),

    NORMAL(2, "正常"),

    DEL(3, "删除"),

    FORBID(4, "禁用"),

    STOP(5, "停用"),

    FREEZE(6, "冻结");

    /**
     * int值
     */
    private int value;

    /**
     * 对应文本
     */
    private String name;

    DataStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

}
