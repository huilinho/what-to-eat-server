package com.what.to.eat.server.controller.admapi;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"后台管理评价接口"})
@RestController
@RequestMapping(value = "/admapi/appraisal")

public class AppraisalController {
}
