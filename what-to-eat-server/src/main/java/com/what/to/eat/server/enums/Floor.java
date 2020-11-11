package com.what.to.eat.server.enums;

import lombok.Getter;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/9 10:40
 */
public enum Floor {
    /**
     * 未知
     */
    UNKNOWN(0, "未知"),
    /**
     * 一楼
     */
    FIRST(1, "一楼"),
    /**
     * 二楼
     */
    SECOND(2, "二楼");

    @Getter
    private int value;

    @Getter
    private String text;

    Floor(int val, String txt) {
        this.value = val;
        this.text = txt;
    }

    public static String getFloor(Integer value) {
      Floor[] values = values();
      for (Floor floor : values) {
        if (floor.value == value) {
          return floor.text;
        }
      }
      return "未知";
    }
}
