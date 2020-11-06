package com.what.to.eat.server.controller.admapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.what.to.eat.server.po.Appraisal;
import com.what.to.eat.server.vaildate.UpdateValid;
import com.what.to.eat.server.web.AdminWebContext;
import net.scode.commons.core.R;
import com.what.to.eat.server.dto.AppraisalDTO;
import com.what.to.eat.server.service.AppraisalService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"后台管理评价接口"})
@RestController
@RequestMapping(value = "/admapi/appraisal")

public class AppraisalController {

    @Autowired
    AppraisalService appraisalService;

    @PostMapping("/")
    public R add(@Validated @RequestBody AppraisalDTO appraisalDTO){
        Appraisal appraisal = appraisalDTO.toAppraisal();
        boolean temp = appraisalService.save(appraisal);
        return temp ? R.ok("添加成功") : R.error("添加失败");
    }


    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        boolean temp = appraisalService.removeById(id);
        return temp ? R.ok("删除成功") : R.error("删除失败");
    }

    @PutMapping(value = "/")
    public R update(@Validated(UpdateValid.class) @RequestBody AppraisalDTO appraisalDTO){
        if (appraisalDTO.getId() <= 0){
            return R.error("修改失败，id不能为空");
        }
        Appraisal appraisal = appraisalDTO.toAppraisal();
        UpdateWrapper<Appraisal> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",appraisalDTO.getId());
        boolean temp = appraisalService.update(appraisal,updateWrapper);
        return temp ? R.ok("修改成功") : R.error("修改失败");
    }

    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable Integer id){
        Appraisal appraisal = appraisalService.getById(id);
        return R.data(appraisal);
    }


    @GetMapping(value = "/list")
    public R list(@RequestParam(required = false, defaultValue = "") String keywords, Page page) {
        QueryWrapper<Appraisal> queryWrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(keywords)) {
            //搜索关键字可以是菜的id
            queryWrapper.and (i -> i.like("appraisal", keywords));
        }
        //搜索结果降序排列
        queryWrapper.orderByDesc("id");
        Page pageData = appraisalService.page(page,queryWrapper);
        return R.data(pageData);
    }
}

