package com.what.to.eat.server.dto;


import com.what.to.eat.server.po.Appraisal;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

@Data
public class AppraisalDTO {
    private int id;

    @NotNull
    private int user_id;

    @NotNull
    private int dishes_id;

    private String appraisal;

    public Appraisal toAppraisal() {
        Appraisal appraisal = new Appraisal();
        BeanUtils.copyProperties("this",appraisal);
        return appraisal;
    }

}
