package com.what.to.eat.server.controller.admapi;

import cn.hutool.*;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.what.to.eat.server.config.AppConfig;
import com.what.to.eat.server.dto.AdminLoginDTO;
import com.what.to.eat.server.po.Admin;
import com.what.to.eat.server.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import net.scode.commons.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = {"后台管理登录接口"})
@RestController
@RequestMapping(value = "/admapi/")
public class IndexController {
    @Autowired
    private AdminService userService;

    @Autowired
    private AppConfig appConfig;

    @ApiOperation(value = "管理员登录", notes = "登陆成功则携带token返回")
    @PostMapping("/login")
    public R login(@RequestBody @Validated AdminLoginDTO loginDTO) {
        log.debug("loginDTO:{}", loginDTO);
        QueryWrapper<Admin> query = new QueryWrapper<>();
        query.eq("mobile", loginDTO.getUsername());
        Admin admin = userService.getOne(query);
        if (admin == null) {
            return R.error(Consts.FAILED_CODE, "账号不存在");
        }
        System.out.println(SecureUtil.md5(loginDTO.getPassword()));
        if (!admin.getPassword().equals(SecureUtil.md5(loginDTO.getPassword()))) {
            return R.error(Consts.FAILED_CODE, "密码错误");
        }
        //生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", admin.getId());
        claims.put("username", admin.getUsername());
        claims.put("nickname", admin.getNickname());
        claims.put("rank", admin.getMobile());
        String token = JWTUtil.createJWT(claims, appConfig.getJwtSubject(), appConfig.getJwtSecret(), appConfig.getJwtLife());
        return R.ok().put("token", token);
    }

}
