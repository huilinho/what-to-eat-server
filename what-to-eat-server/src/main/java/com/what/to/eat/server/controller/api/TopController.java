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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author DengFaLian
 * @Date 2020/11/9 9:58
 * @Version 1.0
 */
@Slf4j
@Api(tags = {"小程序排行接口"})
@RestController
@RequestMapping(value = "/api/rankingList")
public class TopController {

    @Autowired
    private DishesService dishesService;

    @Autowired
    private AppraisalService appraisalService;

    @GetMapping(value = "/list")
    public R getList() {
      QueryWrapper<Dishes> queryWrapper1 = new QueryWrapper<>();
      List<DishesVo> dishesVos = new ArrayList<>();
      queryWrapper1.orderByDesc("support");
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
