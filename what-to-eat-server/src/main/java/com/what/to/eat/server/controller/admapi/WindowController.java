package com.what.to.eat.server.controller.admapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.what.to.eat.server.dto.WindowDTO;
import com.what.to.eat.server.po.Window;
import com.what.to.eat.server.service.WindowService;
import com.what.to.eat.server.vaildate.UpdateValid;
import com.what.to.eat.server.vo.WindowPack;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import net.scode.commons.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/9 10:37
 */
@Slf4j
@Api(tags = {"窗口管理接口"})
@RestController
@RequestMapping(value = "/admapi/window")
public class WindowController {

    @Autowired
    private WindowService windowService;

    @PostMapping("/")
    public R add(@Validated @RequestBody WindowDTO windowDTO) {
        Window window = windowDTO.toWindow();
        boolean save = windowService.save(window);
        return save ? R.ok("添加成功") : R.error("添加失败");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        boolean remove = windowService.removeById(id);
        return remove ? R.ok("删除成功") : R.error("删除失败");
    }

    @PutMapping("/")
    public R update(@Validated(UpdateValid.class) @RequestBody WindowDTO windowDTO) {
        if (windowDTO.getId() <= 0) {
            return R.error("修改失败，id不能为空");
        }
        Window window = windowDTO.toWindow();
        UpdateWrapper<Window> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", windowDTO.getId());
        boolean update =windowService.update(window, updateWrapper);
        return update ? R.ok("修改成功") : R.error("修改失败");
    }

    @GetMapping("/{id}")
    public R getInfo(@PathVariable Integer id) {
        Window window = windowService.getById(id);
        return R.data(window);
    }

    @GetMapping(value = "/list")
    public R getList(@RequestParam(required = false, defaultValue = "") String keywords, Page page) {
        QueryWrapper<Window> queryWrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(keywords)) {
            queryWrapper.and(a -> a.like("name", keywords));
        }
        queryWrapper.orderByDesc("id");
        Page pageDate = windowService.page(page, queryWrapper);
        return R.data(pageDate);
    }

    @GetMapping(value="/pack/{id}")
    @ApiOperation("获取某个饭堂的所有窗口")
    public R windowList(@PathVariable Integer id){
        List<Window> windowList = windowService.list();
        List<WindowPack> windowPacks = new ArrayList<>();
        for(Window window : windowList){
            if(window.getCanteen()==id) {
                WindowPack windowPack = new WindowPack();
                windowPack.setName(window.getName());
                windowPack.setWindowId(window.getId());
                windowPacks.add(windowPack);
            }
            else{
                continue;
            }
        }
         return R.data(windowPacks);
    }

}
