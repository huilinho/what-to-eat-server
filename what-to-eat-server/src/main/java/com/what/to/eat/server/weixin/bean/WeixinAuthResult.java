package com.what.to.eat.server.weixin.bean;

import lombok.Data;

@Data
public class WeixinAuthResult {
    private String openid;
    private String unionid;
    private String session_key;
    private String errcode;
    private String errmsg;
}
