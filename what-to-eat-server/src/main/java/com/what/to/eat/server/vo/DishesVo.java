package com.what.to.eat.server.vo;


import lombok.Data;


/**
 * @Author DengFaLian
 * @Date 2020/11/9 9:39
 * @Version 1.0
 */
@Data
public class DishesVo {

    /*菜式名*/
    private String name;

    /*菜式图片*/
    private String cover;

    /*点赞数*/
    private int like;

    /*评论数*/
    private int count;


}
