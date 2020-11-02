package com.what.to.eat.server.controller.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/2 10:17
 */

@Api(tags = {"小程序用户接口"})
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
}
