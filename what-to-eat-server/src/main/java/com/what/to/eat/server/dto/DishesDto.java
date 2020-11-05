package com.what.to.eat.server.dto;

import com.what.to.eat.server.po.Dishes;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author DengFaLian
 * @Date 2020/11/5 20:25
 * @Version 1.0
 */
@Data
public class DishesDto {
    private int id;

    @NotBlank(message = "请输入菜名")
    private String name;

    @NotNull
    private int windowId;

    @NotNull
    private int typeId;


    private String commentId;

    private String cover;

    private int like;

    private int hate;

    public Dishes toDishes() {
      Dishes dishes = new Dishes();
      BeanUtils.copyProperties("this", dishes);
      return dishes;
    }
}
