package com.what.to.eat.server.controller.api;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.what.to.eat.server.config.AppConfig;
import com.what.to.eat.server.dto.LoginDTO;
import com.what.to.eat.server.enums.DataStatus;
import com.what.to.eat.server.po.User;
import com.what.to.eat.server.service.UserService;
import com.what.to.eat.server.service.User_sessionService;
import com.what.to.eat.server.weixin.WeixinService;
import com.what.to.eat.server.weixin.bean.WeixinAuthResult;
import com.what.to.eat.server.weixin.bean.WeixinUserInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import net.scode.commons.util.JWTUtil;
import net.scode.commons.util.StringUtil;
import net.scode.commons.web.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/2 10:17
 */

@Slf4j
@Api(tags = {"小程序用户接口"})
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    AppConfig appConfig;

    @Autowired
    User_sessionService user_sessionService;

    @Autowired
    WeixinService weixinService;

    @Autowired
    UserService userService;

    @GetMapping("/code2session")
    public R code2session(@RequestParam String code) {
        WeixinAuthResult authResult = weixinService.code2Session(code);
        log.debug("authResult ->{}", authResult);
        if (authResult == null) {
            return R.error("参数错误");
        }
        if (StringUtil.isNotEmpty(authResult.getOpenid())) {
            user_sessionService.saveSessionKey(authResult.getOpenid(), authResult.getSession_key());
            Map<String, Object> map = new HashMap<>();
            map.put("openid", authResult.getOpenid());
            return R.data(map);
        }
        return R.error("参数错误");
    }

    @PostMapping("/login")
    public R login(HttpServletRequest request, @RequestBody LoginDTO loginDTO) {
        if (!weixinService.signatureValid(loginDTO.getOpenid(), loginDTO.getRawData(), loginDTO.getSignature())) {
            return R.error("参数错误");
        }
        WeixinUserInfo weixinUserInfo = weixinService.decrypt(loginDTO.getOpenid(), loginDTO.getEncryptedData(), loginDTO.getIv());

        User user = userService.addUser(weixinUserInfo);
        //生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("openid", user.getOpenid());
        String token = JWTUtil.createJWT(claims, appConfig.getJwtSubject(), appConfig.getJwtSecret(), appConfig.getJwtLife());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("token", token);
        return R.data(map);
    }

}
