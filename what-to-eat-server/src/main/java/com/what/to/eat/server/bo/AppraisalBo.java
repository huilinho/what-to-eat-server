package com.what.to.eat.server.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author DengFaLian
 * @Date 2020/11/10 23:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class AppraisalBo {

  /*用户id*/
  private Integer userId;

  /*用户名*/
  @NotBlank
  private String username;

  /*用户头像路径*/
  @NotBlank
  private String avatar;

  /*评价*/
  @NotBlank
  private String appraisal;
}
