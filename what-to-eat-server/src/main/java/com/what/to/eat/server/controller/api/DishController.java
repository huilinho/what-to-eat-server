package com.what.to.eat.server.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.what.to.eat.server.po.Appraisal;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.po.Type;
import com.what.to.eat.server.service.AppraisalService;
import com.what.to.eat.server.service.DishesService;
import com.what.to.eat.server.service.TypeService;
import com.what.to.eat.server.vo.DishesVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author DengFaLian
 * @Date 2020/11/9 9:58
 * @Version 1.0
 */
@Slf4j
@Api(tags = {"小程序自主选择接口"})
@RestController
@RequestMapping(value = "/api/selectByOneself")
public class DishController {

    @Autowired
    private DishesService dishesService;

    @Autowired
    private AppraisalService appraisalService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public R getDishType() {
      List<Type> list = typeService.list();
      return R.data(list);
    }

    @GetMapping(value = "/list")
    public R getList(@RequestParam int typeID) {
      QueryWrapper<Dishes> queryWrapper1 = new QueryWrapper<>();
      List<DishesVo> dishesVos = new ArrayList<>();
      if(typeID < 0) {
        return R.error("菜式类型id不能为空");
      }
      queryWrapper1.eq("type_id", typeID);
      queryWrapper1.orderByDesc("id");
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
