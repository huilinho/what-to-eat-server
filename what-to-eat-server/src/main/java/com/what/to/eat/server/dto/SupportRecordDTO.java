package com.what.to.eat.server.dto;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.what.to.eat.server.po.Dishes;
import com.what.to.eat.server.po.SupportRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[support_record]对应实体类
 *
 * @author auto 2020年11月11日
 */
@Data
public class SupportRecordDTO {


    private String openId;

    private int dishesId;

  public SupportRecord toSupportRecord(int userId) {
    SupportRecord supportRecord = new SupportRecord();
    supportRecord.setUserId(userId);
    supportRecord.setDishesId(this.dishesId);
    return supportRecord;
  }
}
