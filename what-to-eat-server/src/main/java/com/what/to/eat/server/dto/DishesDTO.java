package com.what.to.eat.server.dto;

import cn.hutool.core.bean.BeanUtil;
import com.what.to.eat.server.po.Dishes;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author DengFaLian
 * @Date 2020/11/5 20:25
 * @Version 1.0
 */
@Data
public class DishesDTO {
    private int id;

    @NotBlank(message = "请输入菜名")
    private String name;

    @NotNull
    private int windowId;

    @NotNull
    private int typeId;

    private String cover;

    private int support;


    public Dishes toDishes() {
      Dishes dishes = new Dishes();
      BeanUtil.copyProperties(this, dishes);
      return dishes;
    }
}
