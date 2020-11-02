package com.what.to.eat.server.weixin.bean;

import lombok.Data;

@Data
public class WeixinToken {
    private String access_token;
    private int expires_in;
    private int errcode;
    private String errmsg;
}
