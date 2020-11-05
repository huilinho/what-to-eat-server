package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[user_session]对应实体类
 * 
 * @author auto 2020年11月05日 
 */
@Data
@TableName(value = "`user_session`")
@ApiModel(value = "表[user_session]的实体类")
public class User_session {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", dataType = "String")
    @TableField("`openid`")
    private String openid;

    /**
     * 微信login后的session_key
     */
    @ApiModelProperty(value = "微信login后的session_key", dataType = "String")
    @TableField("`session_key`")
    private String sessionKey;

}