package com.what.to.eat.server.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.what.to.eat.server.po.Appraisal;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.service.AppraisalService;
import com.what.to.eat.server.service.DishesService;
import com.what.to.eat.server.vo.DishesVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author DengFaLian
 * @Date 2020/11/9 22:52
 * @Version 1.0
 */
@Slf4j
@Api(tags = {"小程序首页接口"})
@RestController
@RequestMapping(value = "/api/homePage")
public class HomePageController {

    @Autowired
    private DishesService dishesService;

    @Autowired
    private AppraisalService appraisalService;

    @GetMapping("/slideshow/{count}")
    public R getSlideshow(@PathVariable Integer count) {
      if(count < 0) return R.error("count不能小于0");
      QueryWrapper<Dishes> queryWrapper = new QueryWrapper<>();
      queryWrapper.last("limit " + count);
      List<Dishes> list = dishesService.list(queryWrapper);
      return R.data(list);
    }

    @GetMapping("/recommend/{amount}")
    public R getRecommend(@PathVariable Integer amount) {
      if(amount < 0) return R.error("count不能小于0");
      QueryWrapper<Dishes> queryWrapper1 = new QueryWrapper<>();
      List<DishesVo> dishesVos = new ArrayList<>();
      queryWrapper1.orderByDesc("support");
      queryWrapper1.last("limit " + amount);
      List<Dishes> dishes = dishesService.list(queryWrapper1);
      for (Dishes dish : dishes) {
        QueryWrapper<Appraisal> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("dishes_id", dish.getId());
        int count = appraisalService.count(queryWrapper2);
        DishesVo dishesVo = new DishesVo();
        dishesVo.setName(dish.getName());
        dishesVo.setCover(dish.getCover());
        dishesVo.setLike(dish.getSupport());
        dishesVo.setCount(count);
        dishesVos.add(dishesVo);
      }
      return R.data(dishesVos);
    }
}
