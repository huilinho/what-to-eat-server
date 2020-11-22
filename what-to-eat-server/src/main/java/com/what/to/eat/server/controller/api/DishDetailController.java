package com.what.to.eat.server.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.what.to.eat.server.bo.AppraisalBo;
import com.what.to.eat.server.dto.AppraisalDTO;
import com.what.to.eat.server.dto.SupportRecordDTO;
import com.what.to.eat.server.enums.Canteen;
import com.what.to.eat.server.enums.Floor;
import com.what.to.eat.server.po.*;
import com.what.to.eat.server.service.*;
import com.what.to.eat.server.vo.DishDetailVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author DengFaLian
 * @Date 2020/11/10 9:59
 * @Version 1.0
 */
@Slf4j
@Api(tags = {"小程序菜式详情接口"})
@RestController
@RequestMapping(value = "/api/dishDetail")
public class DishDetailController {

    @Autowired
    private DishesService dishesService;

    @Autowired
    private AppraisalService appraisalService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private WindowService windowService;

    @Autowired
    private SupportRecordService supportRecordService;

    @GetMapping("/{id}")
    public R getDetail(@PathVariable Integer id) {
      if(id < 0) return R.error("id不能为空");
      QueryWrapper<Appraisal> queryWrapper1 = new QueryWrapper<>();
      QueryWrapper<Type> queryWrapper2 = new QueryWrapper<>();
      List<AppraisalBo> appraisalList = new ArrayList<>();
      String location = null;
      Dishes dish = dishesService.getById(id);
      Window window = windowService.getById(dish.getWindowId());
      location = Floor.getFloor(window.getFloor()) + "." + Canteen.getCanteen(window.getCanteen()) + "." + window.getName();
      queryWrapper1.eq("dishes_id", id);
      List<Appraisal> list = appraisalService.list(queryWrapper1);
      for (Appraisal appraisal : list) {
        User user = userService.getById(appraisal.getUserId());
        if(user == null) return R.error("该评论用户不存在！");
        AppraisalBo appraisalBo = new AppraisalBo(user.getId(), user.getUsername(), user.getAvatar(), appraisal.getAppraisal());
        appraisalList.add(appraisalBo);
      }
      queryWrapper2.eq("id", dish.getTypeId());
      Type dishType = typeService.getOne(queryWrapper2);
      DishDetailVo dishDetail = new DishDetailVo(dish.getId(), dish.getName(), dish.getCover(), dish.getSupport(), dishType.getName(), location, appraisalList);
      return R.data(dishDetail);
    }

    @PutMapping("/")
    public R updateSupport(@RequestBody SupportRecordDTO supportRecordDto) {
      Dishes dish = dishesService.getById(supportRecordDto.getDishesId());
      dish.setSupport(dish.getSupport() + 1);
      UpdateWrapper<Dishes> updateWrapper = new UpdateWrapper<>();
      updateWrapper.eq("id", dish.getId());
      boolean update = dishesService.update(dish, updateWrapper);
      SupportRecord supportRecord = supportRecordDto.toSupportRecord();
      boolean save = supportRecordService.save(supportRecord);
      return (update && save) ? R.ok("修改成功") : R.error("修改失败");
    }

    @PostMapping("/")
    public R addAppraisal(@Validated AppraisalDTO appraisalDTO) {
      Appraisal appraisal = appraisalDTO.toAppraisal();
      boolean save = appraisalService.save(appraisal);
      return save ? R.ok("添加成功") : R.error("添加失败");
    }
}
