package com.what.to.eat.server.dto;

import cn.hutool.core.bean.BeanUtil;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.po.Type;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/9 11:06
 */
@Data
public class TypeDTO {

    private int id;

    @NotBlank(message = "请输入种类名")
    private String name;

    public Type toType(){
        Type type = new Type();
        BeanUtil.copyProperties(this, type);
        return type;
    }
}
