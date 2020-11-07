package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[attachment]对应实体类
 * 
 * @author auto 2020年11月07日 
 */
@Data
@TableName(value = "`attachment`")
@ApiModel(value = "表[attachment]的实体类")
public class Attachment {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 存储路径
     */
    @ApiModelProperty(value = "存储路径", dataType = "String")
    @TableField("`path`")
    private String path;

    /**
     * 对外路径
     */
    @ApiModelProperty(value = "对外路径", dataType = "String")
    @TableField("`url`")
    private String url;

    /**
     * 文件sha1
     */
    @ApiModelProperty(value = "文件sha1", dataType = "String")
    @TableField("`sha1`")
    private String sha1;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    @TableField("`create_time`")
    private java.util.Date createTime;

}