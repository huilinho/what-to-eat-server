package com.what.to.eat.server.dto;

import cn.hutool.core.bean.BeanUtil;
import com.what.to.eat.server.po.Appraisal;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author  WangShuoJun
 * @Data 2020/11/5 22:25
 * @version 1.0
 */

@Data
public class AppraisalDTO {
    private int id;

    @NotNull
    private int userId;

    @NotNull
    private int dishesId;

    @NotBlank(message = "请输入评价")
    private String appraisal;

    public Appraisal toAppraisal() {
        Appraisal appraisal = new Appraisal();
        BeanUtil.copyProperties(this, appraisal);
        return  appraisal;
    }

}
