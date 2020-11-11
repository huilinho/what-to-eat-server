package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[user]对应实体类
 *
 * @author auto 2020年11月02日
 */
@Data
@TableName(value = "`user`")
@ApiModel(value = "表[user]的实体类")
public class User {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid", dataType = "String")
    @TableField("`openid`")
    private String openid;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称", dataType = "String")
    @TableField("`username`")
    private String username;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", dataType = "String")
    @TableField("`avatar`")
    private String avatar;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    @TableField("`create_time`")
    private java.util.Date createTime;

    /**
     * 通用状态,2正常,3删除
     */
    @ApiModelProperty(value = "通用状态,2正常,3删除", dataType = "int")
    @TableField("`data_status`")
    private int dataStatus;

}
