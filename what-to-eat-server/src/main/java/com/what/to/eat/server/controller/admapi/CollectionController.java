package com.what.to.eat.server.controller.admapi;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DengFaLian
 * @Date 2020/11/5 20:21
 * @Version 1.0
 */
@Slf4j
@Api(tags = {"后台管理收藏接口"})
@RestController
@RequestMapping(value = "/admapi/collection")
public class CollectionController {
}
