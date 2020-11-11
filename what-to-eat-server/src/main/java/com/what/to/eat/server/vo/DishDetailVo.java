package com.what.to.eat.server.vo;

import com.what.to.eat.server.bo.AppraisalBo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author DengFaLian
 * @Date 2020/11/10 9:59
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class DishDetailVo {
  /*菜id*/
  private Integer id;
  /*菜名*/
  private String name;

  /*图片路径*/
  private String cover;

  /*推荐分数*/
  private int support;

  /*分类*/
  private String typename;

  /*位置*/
  private String location;
  /*评论*/
  private List<AppraisalBo> appraisalList;
}
