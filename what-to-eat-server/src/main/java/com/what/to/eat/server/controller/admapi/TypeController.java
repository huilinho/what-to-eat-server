package com.what.to.eat.server.controller.admapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.what.to.eat.server.dto.TypeDTO;
import com.what.to.eat.server.po.Type;
import com.what.to.eat.server.service.TypeService;
import com.what.to.eat.server.vaildate.UpdateValid;
import com.what.to.eat.server.vo.TypePack;
import io.swagger.annotations.Api;
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
 * @date 2020/11/9 11:11
 */
@Slf4j
@RestController
@Api(tags="种类管理接口")
@RequestMapping("/admapi/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping("/")
    public R add(@Validated @RequestBody TypeDTO typeDTO) {
        Type type = typeDTO.toType();
        boolean save = typeService.save(type);
        return save ? R.ok("添加成功") : R.error("添加失败");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        boolean remove = typeService.removeById(id);
        return remove ? R.ok("删除成功") : R.error("删除失败");
    }

    @PutMapping("/")
    public R update(@Validated(UpdateValid.class) @RequestBody TypeDTO typeDTO) {
        if (typeDTO.getId() <= 0) {
            return R.error("修改失败，id不能为空");
        }
        Type type = typeDTO.toType();
        UpdateWrapper<Type> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", typeDTO.getId());
        boolean update = typeService.update(type, updateWrapper);
        return update ? R.ok("修改成功") : R.error("修改失败");
    }

    @GetMapping("/{id}")
    public R getInfo(@PathVariable Integer id) {
        Type type = typeService.getById(id);
        return R.data(type);
    }

    @GetMapping(value = "/list")
    public R getList(@RequestParam(required = false, defaultValue = "") String keywords, Page page) {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(keywords)) {
            queryWrapper.and(a -> a.like("name", keywords));
        }
        Page pageDate = typeService.page(page, queryWrapper);
        return R.data(pageDate);
    }

    @GetMapping(value="/pack")
    public R TypeList(){
       List<Type> typeList = typeService.list();
       List<TypePack> typePacks = new ArrayList<>();
       for(Type type : typeList){
           TypePack typePack = new TypePack();
           typePack.setName(type.getName());
           typePack.setTypeId(type.getId());
           typePacks.add(typePack);
       }
        return R.data(typePacks);
    }
}
