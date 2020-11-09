package com.what.to.eat.server.dto;

import cn.hutool.core.bean.BeanUtil;
import com.what.to.eat.server.po.Window;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/9 10:37
 */
@Data
public class WindowDTO {
    private int id;

    @NotBlank(message = "请输入窗口名")
    private String name;

    private String url;

    private int floor;

    private int canteen;

    public Window toWindow() {
        Window window = new Window();
        BeanUtil.copyProperties(this, window);
        return window;
    }
}
