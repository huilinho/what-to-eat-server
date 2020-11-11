package com.what.to.eat.server.enums;

import lombok.Getter;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/9 10:41
 */
public enum Canteen {
    /**
     * 未知
     */
    UNKNOWN(0, "未知"),
    /**
     * 一饭
     */
    FIRST(1, "一饭"),
    /**
     * 二饭
     */
    SECOND(2, "二饭"),
    /**
     * 三饭
     */
    THIRD(3, "三饭"),
    /**
     * 四饭
     */
    FOURTH(4, "四饭");


    @Getter
    private int value;

    @Getter
    private String text;

    Canteen(int val, String txt) {
        this.value = val;
        this.text = txt;
    }

  public static String getCanteen(Integer value) {
    Canteen[] values = values();
    for (Canteen canteen : values) {
      if (canteen.value == value) {
        return canteen.text;
      }
    }
    return "未知";
  }
}
