package com.what.to.eat.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[window]对应实体类
 * 
 * @author auto 2020年11月09日 
 */
@Data
@TableName(value = "`window`")
@ApiModel(value = "表[window]的实体类")
public class Window {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 窗口名称
     */
    @ApiModelProperty(value = "窗口名称", dataType = "String")
    @TableField("`name`")
    private String name;

    /**
     * 窗口图片url
     */
    @ApiModelProperty(value = "窗口图片url", dataType = "String")
    @TableField("`url`")
    private String url;

    /**
     * 饭堂楼层,1：一楼，2：二楼
     */
    @ApiModelProperty(value = "饭堂楼层,1：一楼，2：二楼", dataType = "int")
    @TableField("`floor`")
    private int floor;

    /**
     * 饭堂，1：一饭；2：二饭；3：三饭；4：四饭
     */
    @ApiModelProperty(value = "饭堂，1：一饭；2：二饭；3：三饭；4：四饭", dataType = "int")
    @TableField("`canteen`")
    private int canteen;

}