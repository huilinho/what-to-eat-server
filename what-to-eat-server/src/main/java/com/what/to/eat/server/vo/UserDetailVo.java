package com.what.to.eat.server.vo;

import com.what.to.eat.server.bo.DynamicBo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author DengFaLian
 * @Date 2020/11/11 14:38
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class UserDetailVo {

    /*用户名*/
    private String name;

    /*评价数*/
    private int AppraisalAmount;

    /*评论数*/
    private int CommentAmount;

    /*动态*/
    private List<DynamicBo> dynamicBoList;
}
