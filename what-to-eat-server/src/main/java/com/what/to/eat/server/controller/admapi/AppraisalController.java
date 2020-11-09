package com.what.to.eat.server.controller.admapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.what.to.eat.server.po.Appraisal;
import com.what.to.eat.server.vaildate.UpdateValid;
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
@Api(tags = {"评价管理接口"})
@RestController
@RequestMapping(value = "/admapi/appraisal")

public class AppraisalController {

    @Autowired
    AppraisalService appraisalService;


    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        boolean temp = appraisalService.removeById(id);
        return temp ? R.ok("删除成功") : R.error("删除失败");
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
            //搜索关键字可以是评论里出现的字
            queryWrapper.and (i -> i.like("appraisal", keywords));
        }
        //搜索结果降序排列
        queryWrapper.orderByDesc("id");
        Page pageData = appraisalService.page(page,queryWrapper);
        return R.data(pageData);
    }


}

