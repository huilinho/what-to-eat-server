package com.what.to.eat.server.controller.admapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.what.to.eat.server.dto.AdminDTO;
import com.what.to.eat.server.po.Admin;
import com.what.to.eat.server.service.AdminService;
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
 * @date 2020/11/1 23:21
 */
@Slf4j
@Api(tags = {"管理员接口"})
@RestController
@RequestMapping(value = "/admapi/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/info")
    public R info() {
        int userId = AdminWebContext.getUserId();
        Admin admin = adminService.getById(userId);
        admin.setPassword(null);
        return R.data(admin);
    }

    @GetMapping(value = "/list")
    public R list(@RequestParam(required = false, defaultValue = "") String keywords, Page page) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(keywords)) {
            //搜索关键字可以是手机号或者用户名
            queryWrapper.and(a -> a.or(q -> q.like("mobile", keywords)).or(q -> q.like("username", keywords)));
        }
          //搜索结果降序排列
        queryWrapper.orderByDesc("id");
        Page pageData = adminService.page(page, queryWrapper);
        return R.data(pageData);
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        boolean temp = adminService.removeById(id);
        return temp ? R.ok("删除成功") : R.error("删除失败");
    }

    @PutMapping(value = "/")
    public R update(@Validated(UpdateValid.class) @RequestBody AdminDTO adminDTO) {
        if (adminDTO.getId() <= 0) {
            return R.error("修改失败，id不能为空");
        }
        Admin admin = adminDTO.toAdmin();
        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", adminDTO.getId());
        boolean temp = adminService.update(admin,updateWrapper);
        return temp ? R.ok("修改成功") : R.error("修改失败");
    }

    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable Integer id){
        Admin admin = adminService.getById(id);
        return R.data(admin);
    }

    @PostMapping("/")
    public R add(@Validated @RequestBody AdminDTO adminDTO) {
        Admin admin = adminDTO.toAdmin();
        boolean temp = adminService.save(admin);
        return temp ? R.ok("添加成功") : R.error("添加失败");
    }
}
