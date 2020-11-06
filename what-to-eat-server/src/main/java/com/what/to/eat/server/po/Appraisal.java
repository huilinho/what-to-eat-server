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
 * 表[appraisal]对应实体类
 * 
 * @author auto 2020年11月05日 
 */
@Data
@TableName(value = "`appraisal`")
@ApiModel(value = "表[appraisal]的实体类")
public class Appraisal {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", dataType = "int")
    @TableField("`user_id`")
    private int userId;

    /**
     *  菜式id
     */
    @ApiModelProperty(value = "菜式id", dataType = "int")
    @TableField("`dishes_id`")
    private int dishesId;

    /**
     * 评价
     */
    @ApiModelProperty(value = "评价", dataType = "String")
    @TableField("`appraisal`")
    private String appraisal;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    @TableField("`create_time`")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private java.util.Date createTime;

}