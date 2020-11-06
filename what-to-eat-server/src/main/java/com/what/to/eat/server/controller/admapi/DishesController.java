package com.what.to.eat.server.controller.admapi;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DengFaLian
 * @Date 2020/11/5 20:24
 * @Version 1.0
 */
@Slf4j
@Api(tags = {"菜式管理接口"})
@RestController
@RequestMapping(value = "/admapi/dishes")
public class DishesController {


}
