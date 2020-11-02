package com.what.to.eat.server.controller.admapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.what.to.eat.server.dto.AdminDTO;
import com.what.to.eat.server.enums.DataStatus;
import com.what.to.eat.server.po.Admin;
import com.what.to.eat.server.po.User;
import com.what.to.eat.server.service.UserService;
import com.what.to.eat.server.vaildate.UpdateValid;
import com.what.to.eat.server.web.AdminWebContext;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import net.scode.commons.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/2 10:18
 */
@Slf4j
@Api(tags = {"用户管理接口"})
@RestController
@RequestMapping(value = "/admapi/user")
public class UserAdminController {
      @Autowired
      UserService userService;


    @GetMapping(value = "/list")
    public R list(@RequestParam(required = false, defaultValue = "") String keywords, Page page) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(keywords)) {
          //根据用户名搜索用户
            queryWrapper.like("username", keywords);
        }
        //搜索结果降序排列
        queryWrapper.orderByDesc("id");
        Page pageData = userService.page(page,queryWrapper);
        return R.data(pageData);
    }

    @PutMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        User user = userService.getById(id);
        user.setDataStatus(DataStatus.DELETE.getValue());
        boolean temp = user.getDataStatus()== DataStatus.DELETE.getValue();
        return temp ? R.ok("用户封锁成功") : R.error("用户封锁失败");
    }

}
