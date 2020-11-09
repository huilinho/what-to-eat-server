package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[type]对应实体类
 *
 * @author auto 2020年11月09日
 */
@Data
@TableName(value = "`type`")
@ApiModel(value = "表[type]的实体类")
public class Type {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 种类名称
     */
    @ApiModelProperty(value = "种类名称", dataType = "String")
    @TableField("`name`")
    private String name;

}
