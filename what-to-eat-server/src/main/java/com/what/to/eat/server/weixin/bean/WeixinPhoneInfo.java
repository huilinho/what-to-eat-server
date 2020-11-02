package com.what.to.eat.server.weixin.bean;

import lombok.Data;

@Data
public class WeixinPhoneInfo {
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private Watermark watermark;

    @Data
    private class Watermark {
        private long timestamp;
        private String appid;
    }
}

