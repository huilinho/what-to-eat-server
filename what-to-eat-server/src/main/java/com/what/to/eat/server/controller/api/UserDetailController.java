package com.what.to.eat.server.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.what.to.eat.server.bo.AppraisalBo;
import com.what.to.eat.server.bo.DynamicBo;
import com.what.to.eat.server.dto.UserDTO;
import com.what.to.eat.server.dto.WindowListDTO;
import com.what.to.eat.server.po.*;
import com.what.to.eat.server.service.*;
import com.what.to.eat.server.vo.UserDetailVo;
import com.what.to.eat.server.vo.WindowListVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

  @GetMapping("/details")
  public R getDetails(@RequestParam String openid) {
    QueryWrapper<SupportRecord> queryWrapper1 = new QueryWrapper();
    QueryWrapper<Appraisal> queryWrapper2 = new QueryWrapper();
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("openid",openid);
    User user = userService.getOne(userQueryWrapper);
    if(user == null)
        return R.error("该用户不存在");
    int userId = user.getId();
    String name = user.getUsername();
    List<DynamicBo> dynamic = new ArrayList<>();
    queryWrapper1.eq("user_id", userId);
    int count1 = supportRecordService.count(queryWrapper1);
    List<SupportRecord> list1 = supportRecordService.list(queryWrapper1);
    for (SupportRecord supportRecord : list1) {
      Dishes dish = dishesService.getById(supportRecord.getDishesId());
      DynamicBo dynamicBo = new DynamicBo("点赞", null, supportRecord.getCreateTime(), dish.getName());
      dynamic.add(dynamicBo);
    }
    queryWrapper2.eq("user_id", userId);
    int count2 = appraisalService.count(queryWrapper2);
    List<Appraisal> list2 = appraisalService.list(queryWrapper2);
    for (Appraisal appraisal : list2) {
      Dishes dish = dishesService.getById(appraisal.getDishesId());
      DynamicBo dynamicBo = new DynamicBo(appraisal.getAppraisal(), appraisal.getCreateTime(), dish.getName());
      dynamic.add(dynamicBo);
    }
    Collections.sort(dynamic, new Comparator<DynamicBo>() {
      @Override
      public int compare(DynamicBo o1, DynamicBo o2) {
        int flag = o2.getCreateTime().compareTo(o1.getCreateTime());
        return flag;
      }
    });
    UserDetailVo userDetailVo = new UserDetailVo(name,count1, count2, dynamic);
    return R.data(userDetailVo);
  }

  @PutMapping("/")
  public R updateUser(@Validated @RequestBody UserDTO userDTO) {
    User user = userDTO.toUser();
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", user.getId());
    boolean update = userService.update(user, updateWrapper);
    return update ? R.data("修改成功") : R.error("修改失败");
  }

}
