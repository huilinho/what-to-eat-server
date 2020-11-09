package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[dishes]对应实体类
 *
 * @author auto 2020年11月05日
 */
@Data
@TableName(value = "`dishes`")
@ApiModel(value = "表[dishes]的实体类")
public class Dishes {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 菜式名
     */
    @ApiModelProperty(value = "菜式名", dataType = "String")
    @TableField("`name`")
    private String name;

    /**
     * 所属窗口id
     */
    @ApiModelProperty(value = "所属窗口id", dataType = "int")
    @TableField("`window_id`")
    private int windowId;

    /**
     * 种类id
     */
    @ApiModelProperty(value = "种类id", dataType = "int")
    @TableField("`type_id`")
    private int typeId;

    /**
     * 菜式图片路径
     */
    @ApiModelProperty(value = "菜式图片路径", dataType = "String")
    @TableField("`cover`")
    private String cover;

    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数", dataType = "int")
    @TableField("`support`")
    private int support;

  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
  @TableField("`create_time`")
  @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
  private java.util.Date createTime;

}
