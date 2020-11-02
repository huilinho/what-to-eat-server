//package com.what.to.eat.server.weixin.impl;
//
//import cn.hutool.core.date.DateTime;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.lang.UUID;
//import cn.hutool.crypto.SecureUtil;
//import cn.hutool.json.JSONUtil;
//import com.what.to.eat.server.config.AppConfig;
//import com.what.to.eat.server.config.WeixinConfig;
//import com.what.to.eat.server.service.UserSessionService;
//import com.what.to.eat.server.weixin.WeixinService;
//import com.what.to.eat.server.weixin.bean.UnifiedResponseData;
//import com.what.to.eat.server.weixin.bean.UnifiedResquestData;
//import com.what.to.eat.server.weixin.bean.WeixinAuthResult;
//import com.what.to.eat.server.weixin.bean.WeixinPhoneInfo;
//import com.what.to.eat.server.weixin.bean.WeixinToken;
//import com.what.to.eat.server.weixin.bean.WeixinUserInfo;
//import com.what.to.eat.server.weixin.util.Util;
//import lombok.extern.slf4j.Slf4j;
//import net.scode.commons.util.HttpsUtil;
//import org.apache.commons.codec.binary.Base64;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.File;
//import java.io.InputStream;
//import java.security.AlgorithmParameters;
//import java.security.Key;
//import java.security.Security;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Map;
//
//@Slf4j
//@Service
//public class WeixinServiceImpl implements WeixinService {
//    public static WeixinToken weixinToken;
//    public static Date accessTokenLastTime;
//
//    private static String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
//    private static String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session";
//    private static String getwxacodeUnlimitUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";
//    private static String getwxacodeLimitUrl = "https://api.weixin.qq.com/wxa/getwxacode";
//
//    private static String unifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//
//    private static boolean initialized = false;
//    @Autowired
//    private WeixinConfig weixinConfig;
//
//    @Autowired
//    private AppConfig appConfig;
//
//    @Autowired
//    private UserSessionService userSessionService;
//
//    private WeixinToken getWeixinToken() {
//        try {
//            accessTokenLastTime = DateUtil.date();
//            String url = tokenUrl + "?grant_type=client_credential&appid=" + weixinConfig.getAppId() + "&secret=" + weixinConfig.getAppSecret();
//            String json = HttpsUtil.doGet(url);
//            WeixinToken weixinToken = JSONUtil.toBean(json, WeixinToken.class);
//            return weixinToken;
//        } catch (Exception e) {
//            log.error("getWeixinToken error {}", e);
//        }
//        return null;
//    }
//
//    @Override
//    public String getAccessToken() {
//        if (weixinToken == null) {
//            weixinToken = getWeixinToken();
//        } else {
//            Date expireTime = DateUtil.offsetSecond(accessTokenLastTime, weixinToken.getExpires_in());
//            if (DateUtil.compare(DateUtil.date(), expireTime) > 0) {
//                weixinToken = getWeixinToken();
//            }
//        }
//        return weixinToken.getAccess_token();
//    }
//
//    @Override
//    public WeixinAuthResult code2Session(String code) {
//        String url = code2SessionUrl + "?appid=" + weixinConfig.getAppId() + "&secret=" + weixinConfig.getAppSecret() + "&js_code=" + code + "&grant_type=authorization_code";
//        String json = HttpsUtil.doGet(url);
//        try {
//            WeixinAuthResult weixinAuthResult = JSONUtil.toBean(json, WeixinAuthResult.class);
//            return weixinAuthResult;
//        } catch (Exception e) {
//            log.error("code2Session error {}", e);
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean signatureValid(String openid, String rawData, String signature) {
//        String sessionKey = userSessionService.getSessionKeyByOpenid(openid);
//        if (sessionKey == null) {
//            return false;
//        }
//        String sign = SecureUtil.sha1(rawData + sessionKey);
//        return sign.equals(signature);
//    }
//
//    @Override
//    public WeixinUserInfo decrypt(String openid, String encryptedData, String iv) {
//        /**
//         * 对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充。
//         * 对称解密的目标密文为 Base64_Decode(encryptedData)。
//         * 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
//         * 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
//         */
//        String sessionKey = userSessionService.getSessionKeyByOpenid(openid);
//        if (sessionKey == null) {
//            return null;
//        }
//        try {
//            byte[] resultByte = decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
//            if (null != resultByte && resultByte.length > 0) {
//                String result = decode(resultByte);
//                log.debug("result->{}", result);
//                return JSONUtil.toBean(result, WeixinUserInfo.class);
//
//            }
//        } catch (Exception e) {
//            log.error("decrypt err {}", e);
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public WeixinPhoneInfo decryptPhoneNumber(String openid, String encryptedData, String iv) {
//        String sessionKey = userSessionService.getSessionKeyByOpenid(openid);
//        if (sessionKey == null) {
//            return null;
//        }
//        try {
//            byte[] resultByte = decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
//            if (null != resultByte && resultByte.length > 0) {
//                String result = decode(resultByte);
//                log.debug("result->{}", result);
//                return JSONUtil.toBean(result, WeixinPhoneInfo.class);
//
//            }
//        } catch (Exception e) {
//            log.error("decryptPhoneNumber err {}", e);
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public UnifiedResponseData unifiedOrder(UnifiedResquestData unifiedResquestData) {
//        String xmlStr = Util.toXmlStr(unifiedResquestData, "UTF-8");
//        String xml = HttpsUtil.doPostXml(unifiedOrderUrl, xmlStr);
//        try {
//            UnifiedResponseData unifiedResponseData = (UnifiedResponseData) Util.getObjectFromXML(xml, UnifiedResponseData.class);
//            return unifiedResponseData;
//        } catch (Exception e) {
//            log.error("unifiedOrder error {}", e);
//        }
//        return null;
//    }
//
//    @Override
//    public String getQrCode(int userId, Map<String, Object> param) {
//        String accessToken = getAccessToken();
//        String url = getwxacodeUnlimitUrl + "?access_token=" + accessToken;
//        InputStream in = HttpsUtil.doPostFile(url, param);
//        if (in != null) {
//            String path = "qrcode" + "/" + new DateTime().toString("yyyy-MM") + UUID.randomUUID().toString() + ".png";
//            File outFile = new File(appConfig.getUploadBasePath() + path);
//            FileUtil.writeFromStream(in, outFile);
//            return appConfig.getUploadBaseUrl() + path;
//        }
//        return null;
//    }
//
//    @Override
//    public String getLimitQrCode(int userId, Map<String, Object> param) {
//        String accessToken = getAccessToken();
//        String url = getwxacodeLimitUrl + "?access_token=" + accessToken;
//        InputStream in = HttpsUtil.doPostFile(url, param);
//        if (in != null) {
//            String path = "qrcode" + "/" + new DateTime().toString("yyyy-MM") + UUID.randomUUID().toString() + ".png";
//            File outFile = new File(appConfig.getUploadBasePath() + path);
//            FileUtil.writeFromStream(in, outFile);
//            return appConfig.getUploadBaseUrl() + path;
//        }
//        return null;
//    }
//
//    private byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
//        try {
//            if (!initialized) {
//                Security.addProvider(new BouncyCastleProvider());
//                initialized = true;
//            }
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
//            Key sKeySpec = new SecretKeySpec(keyByte, "AES");
//
//            AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
//            params.init(new IvParameterSpec(ivByte));
//
//            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);// 初始化
//            byte[] result = cipher.doFinal(content);
//            return result;
//        } catch (Exception e) {
//            log.error("decrypt err {}", e);
//        }
//        return null;
//    }
//
//    private static String decode(byte[] decrypted) {
//        int pad = decrypted[decrypted.length - 1];
//        if (pad < 1 || pad > 32) {
//            pad = 0;
//        }
//        return new String(Arrays.copyOfRange(decrypted, 0, decrypted.length - pad));
//    }
//}
