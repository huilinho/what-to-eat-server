package com.what.to.eat.server.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author DengFaLian
 * @Date 2020/11/11 14:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class DynamicBo {

    /*评价*/
    private String support;

    /*评论*/
    private String appraisal;

    /*创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private java.util.Date createTime;

    /*菜式名字*/
    private String dishName;

  public DynamicBo() {
  }

//  public DynamicBo(int support, Date createTime, String dishName) {
//    this.support = support;
//    this.createTime = createTime;
//    this.dishName = dishName;
//  }

  public DynamicBo(String appraisal, Date createTime, String dishName) {
    this.appraisal = appraisal;
    this.createTime = createTime;
    this.dishName = dishName;
  }

}
