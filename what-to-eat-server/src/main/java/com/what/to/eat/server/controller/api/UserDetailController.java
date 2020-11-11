package com.what.to.eat.server.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.what.to.eat.server.bo.AppraisalBo;
import com.what.to.eat.server.bo.DynamicBo;
import com.what.to.eat.server.po.Appraisal;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.po.SupportRecord;
import com.what.to.eat.server.service.AppraisalService;
import com.what.to.eat.server.service.DishesService;
import com.what.to.eat.server.service.SupportRecordService;
import com.what.to.eat.server.service.UserService;
import com.what.to.eat.server.vo.UserDetailVo;
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
 * @Date 2020/11/11 12:34
 * @Version 1.0
 */
@Slf4j
@Api(tags = {"小程序用户详情接口"})
@RestController
@RequestMapping(value = "/api/userDetail")
public class UserDetailController {

  @Autowired
  private DishesService dishesService;

  @Autowired
  private UserService userService;

  @Autowired
  private AppraisalService appraisalService;

  @Autowired
  private SupportRecordService supportRecordService;

  @GetMapping("/{userId}")
  public R getDetails(@PathVariable int userId) {
    QueryWrapper<SupportRecord> queryWrapper1 = new QueryWrapper();
    QueryWrapper<Appraisal> queryWrapper2 = new QueryWrapper();
    List<DynamicBo> dynamic = new ArrayList<>();
    queryWrapper1.eq("user_id", userId);
    int count1 = supportRecordService.count(queryWrapper1);
    queryWrapper2.eq("user_id", userId);
    int count2 = appraisalService.count(queryWrapper2);
    List<Appraisal> list = appraisalService.list(queryWrapper2);
    for (Appraisal appraisal : list) {
      Dishes dish = dishesService.getById(appraisal.getDishesId());
      DynamicBo dynamicBo = new DynamicBo(appraisal.getAppraisal(), appraisal.getCreateTime(), dish.getName());
      dynamic.add(dynamicBo);
    }
    UserDetailVo userDetailVo = new UserDetailVo(count1, count2, dynamic);
    return R.data(userDetailVo);
  }
}
