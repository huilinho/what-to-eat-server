package com.what.to.eat.server.weixin;

import com.what.to.eat.server.weixin.bean.UnifiedResponseData;
import com.what.to.eat.server.weixin.bean.UnifiedResquestData;
import com.what.to.eat.server.weixin.bean.WeixinAuthResult;
import com.what.to.eat.server.weixin.bean.WeixinPhoneInfo;
import com.what.to.eat.server.weixin.bean.WeixinUserInfo;

import java.util.Map;

public interface WeixinService {
    String getAccessToken();

    WeixinAuthResult code2Session(String code);

    boolean signatureValid(String openid, String rawData, String signature);

    WeixinUserInfo decrypt(String openid, String encryptedData, String iv);

    WeixinPhoneInfo decryptPhoneNumber(String openid, String encryptedData, String iv);

    UnifiedResponseData unifiedOrder(UnifiedResquestData unifiedResquestData);

    String getQrCode(int userId, Map<String,Object > param);
    String getLimitQrCode(int userId, Map<String,Object > param);
}
