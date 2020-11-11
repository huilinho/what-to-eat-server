package com.what.to.eat.server.dto;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 表[user]对应实体类
 *
 * @author auto 2020年11月02日
 */
@Data
public class UserDTO {
    private int id;

    private String openid;

    @NotBlank
    private String username;

    private String avatar;

    private int dataStatus;

  public User toUser() {
    User user = new User();
    BeanUtil.copyProperties(this, user);
    return user;
  }
}
