package com.what.to.eat.server;

import com.what.to.eat.server.enums.Floor;

/**
 * @Author DengFaLian
 * @Date 2020/11/11 19:04
 * @Version 1.0
 */
public class test {
  public static void main(String[] args) {
    String floor = Floor.getFloor(1);
    System.out.println(floor);
  }
}
