package com.what.to.eat.server.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/1 23:15
 */
@Data
public class AdminLoginDTO {
    @ApiModelProperty(value = "手机号", dataType = "String", required = true)
    @Pattern(regexp = "^\\d{11}$", message = "请输入11号手机号码！")
    private String username;

    @ApiModelProperty(value = "密码", dataType = "String", required = true)
    @Pattern(regexp = "^[A-Za-z0-9$@#%^&]{6,20}$", message = "密码只能由字母、数字、$、@、#、%、^、&构成,最少6位最高20位！")
    private String password;

}
