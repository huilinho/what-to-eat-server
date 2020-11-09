package com.what.to.eat.server.controller.admapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.what.to.eat.server.dto.DishesDTO;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.service.DishesService;
import com.what.to.eat.server.vaildate.UpdateValid;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import net.scode.commons.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

  @Autowired
  private DishesService dishesService;

  @PostMapping("/")
  public R add(@Validated @RequestBody DishesDTO dishesDto) {
    Dishes dishes = dishesDto.toDishes();
    boolean save = dishesService.save(dishes);
    return save ? R.ok("添加成功") : R.error("添加失败");
  }

  @DeleteMapping("/{id}")
  public R delete(@PathVariable Integer id) {
    boolean remove = dishesService.removeById(id);
    return remove ? R.ok("删除成功") : R.error("删除失败");
  }

  @PutMapping("/")
  public R update(@Validated(UpdateValid.class) @RequestBody DishesDTO dishesDto) {
    if (dishesDto.getId() <= 0) {
      return R.error("修改失败，id不能为空");
    }
    Dishes dishes = dishesDto.toDishes();
    UpdateWrapper<Dishes> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", dishesDto.getId());
    boolean update = dishesService.update(dishes, updateWrapper);
    return update ? R.ok("修改成功") : R.error("修改失败");
  }

  @GetMapping("/{id}")
  public R getInfo(@PathVariable Integer id) {
    Dishes dish = dishesService.getById(id);
    return R.data(dish);
  }
  @GetMapping(value = "/list")
  public R getList(@RequestParam(required = false, defaultValue = "") String keywords, Page page) {
    QueryWrapper<Dishes> queryWrapper = new QueryWrapper<>();
    if(StringUtil.isNotEmpty(keywords)) {
      queryWrapper.and(a -> a.like("name", keywords));
    }
    queryWrapper.orderByDesc("id");
    Page pageDate = dishesService.page(page, queryWrapper);
    return R.data(pageDate);
  }

}
