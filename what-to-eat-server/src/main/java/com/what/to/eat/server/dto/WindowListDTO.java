package com.what.to.eat.server.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author DengFaLian
 * @Date 2020/11/11 21:29
 * @Version 1.0
 */
@Data
public class WindowListDTO {

  @NotNull
   private int floor;

  @NotNull
   private int canteen;
}
