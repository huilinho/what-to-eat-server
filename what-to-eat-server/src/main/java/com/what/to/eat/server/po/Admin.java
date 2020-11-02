package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[admin]对应实体类
 *
 * @author auto 2020年11月01日
 */
@Data
@TableName(value = "`admin`")
@ApiModel(value = "表[admin]的实体类")
public class Admin {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", dataType = "String")
    @TableField("`mobile`")
    private String mobile;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", dataType = "String")
    @TableField("`password`")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", dataType = "String")
    @TableField("`nickname`")
    private String nickname;

    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名", dataType = "String")
    @TableField("`username`")
    private String username;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", dataType = "String")
    @TableField("`avatar`")
    private String avatar;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间", dataType = "java.util.Date")
    @TableField("`last_time`")
    private java.util.Date lastTime;

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
