package com.what.to.eat.server.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.what.to.eat.server.dto.WindowListDTO;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.po.Window;
import com.what.to.eat.server.service.DishesService;
import com.what.to.eat.server.service.WindowService;
import com.what.to.eat.server.vo.WindowListVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author DengFaLian
 * @Date 2020/11/11 20:20
 * @Version 1.0
 */
@Slf4j
@Api(tags = {"小程序窗口列表接口"})
@RestController
@RequestMapping(value = "/api/windowDishesList")
public class WindowListController {

    @Autowired
    private WindowService windowService;

    @Autowired
    private DishesService dishesService;

    @GetMapping("/list")
    public R getWindowList(@Validated WindowListDTO windowListDTO) {
      QueryWrapper<Window> queryWrapper = new QueryWrapper<>();
      queryWrapper.and(a ->a.eq("floor", windowListDTO.getFloor()).eq("canteen", windowListDTO.getCanteen()));
      List<Window> list = windowService.list(queryWrapper);
      List<WindowListVo> windowList = new ArrayList<>();
      for (Window window : list) {
        String dishesList = "";
        QueryWrapper<Dishes> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("window_id", window.getId());
        queryWrapper.last("limit 5");
        List<Dishes> list1 = dishesService.list(queryWrapper1);
        for (Dishes dish : list1) {
          dishesList += "、" + dish.getName();
        }
        WindowListVo windowListVo = new WindowListVo(window.getName(), window.getUrl(), dishesList);
        windowList.add(windowListVo);
      }
      return R.data(windowList);
    }
}
