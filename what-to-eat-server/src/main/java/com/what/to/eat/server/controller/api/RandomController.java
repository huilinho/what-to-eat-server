package com.what.to.eat.server.controller.api;

import com.what.to.eat.server.service.DishesService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Author DengFaLian
 * @Date 2020/11/30 11:18
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/random")
@Api(tags = "小程序随机接口")
public class RandomController {

  @Autowired
  private DishesService dishesService;

  @GetMapping("/")
  public int getRandom() {
    int count = dishesService.count();
    System.out.println(count);
    int id = (int) (Math.random() * count);
    return id + 1;
  }
}
