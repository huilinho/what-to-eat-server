package com.what.to.eat.server.dto;

import cn.hutool.core.bean.BeanUtil;
import com.what.to.eat.server.enums.DataStatus;
import com.what.to.eat.server.po.Admin;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/1 23:48
 */
@Data
public class AdminDTO {
    private int id;

    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @NotBlank(message = "请输入昵称")
    private String nickname;
    @NotBlank(message = "请输入姓名")
    private String username;

    private String password;

    private String avatar;



    public Admin toAdmin() {
        Admin admin = new Admin();
        BeanUtil.copyProperties(this, admin);
        admin.setDataStatus(DataStatus.NORMAL.getValue());
        return admin;
    }
}
