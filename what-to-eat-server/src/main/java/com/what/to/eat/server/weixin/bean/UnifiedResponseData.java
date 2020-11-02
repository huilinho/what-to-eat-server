package com.what.to.eat.server.weixin.bean;

import lombok.Data;

@Data
public class UnifiedResponseData {
    //返回状态码
    private String return_code;

    private String return_msg;
    //return_code == success时有返回
    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    //交易状态码
    private String result_code;
    private String err_code;
    private String err_code_des;

    //return_code ==success &&result_code == success时有返回
    //JSAPI 交易类型，取值为：JSAPI，NATIVE，APP等，说明详见参数规定
    private String trade_type;
    //预支付交易会话标志
    private String prepay_id;
    //trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
    private String code_url;

}
