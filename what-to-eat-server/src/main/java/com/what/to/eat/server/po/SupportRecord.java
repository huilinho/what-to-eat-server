package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[support_record]对应实体类
 * 
 * @author auto 2020年11月11日 
 */
@Data
@TableName(value = "`support_record`")
@ApiModel(value = "表[support_record]的实体类")
public class SupportRecord {

    /**
     * 
     */
    @ApiModelProperty(value = "", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 点赞用户
     */
    @ApiModelProperty(value = "点赞用户", dataType = "int")
    @TableField("`user_id`")
    private int userId;

    /**
     * 点赞菜式
     */
    @ApiModelProperty(value = "点赞菜式", dataType = "int")
    @TableField("`dishes_id`")
    private int dishesId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    @TableField("`create_time`")
    private java.util.Date createTime;

}