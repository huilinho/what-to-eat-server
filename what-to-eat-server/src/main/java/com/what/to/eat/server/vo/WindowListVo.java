package com.what.to.eat.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author DengFaLian
 * @Date 2020/11/11 19:35
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class WindowListVo {

    private String windowName;

    private String url;

    private String dishNameList;
}
