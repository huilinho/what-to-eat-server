package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[collection]对应实体类
 * 
 * @author auto 2020年11月05日 
 */
@Data
@TableName(value = "`collection`")
@ApiModel(value = "表[collection]的实体类")
public class Collection {

    /**
     * 
     */
    @ApiModelProperty(value = "", dataType = "int")
    @TableField("`Id`")
    private int Id;

    /**
     * 
     */
    @ApiModelProperty(value = "", dataType = "int")
    @TableField("`user_id`")
    private int userId;

    /**
     * 
     */
    @ApiModelProperty(value = "", dataType = "int")
    @TableField("`dishes_id`")
    private int dishesId;

}