package com.what.to.eat.server.weixin.bean;

import lombok.Data;

@Data
public class WeixinUserInfo {
    private String openId;
    private String unionId;
    private String nickName;
    private String gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private Watermark watermark;

    @Data
    private class Watermark {
        private long timestamp;
        private String appid;
    }
}

