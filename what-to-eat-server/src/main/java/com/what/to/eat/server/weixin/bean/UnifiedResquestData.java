package com.what.to.eat.server.weixin.bean;


import cn.hutool.core.util.RandomUtil;
import com.what.to.eat.server.weixin.util.Signature;
import lombok.Data;
import net.scode.commons.util.StringUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
public class UnifiedResquestData {
    // 每个字段具体的意思请查看API文档
    private String appid = "";
    private String mch_id = "";
    private String device_info = "";
    private String nonce_str = "";
    private String sign = "";
    private String body = "";
    private String detail = "";
    private String attach = "";
    private String out_trade_no = "";
    private int total_fee = 0;
    private String spbill_create_ip = "";
    private String time_start = "";
    private String time_expire = "";
    private String goods_tag = "";
    private String openid = "";
    // native支付需要的参数
    private String notify_url;
    private String trade_type;
    private String product_id;

    public UnifiedResquestData(String appid, String mchid, String key, String trade_type, String openid, String body, String detail, String attach,
                               String outTradeNo, int totalFee, String deviceInfo, String spBillCreateIP, String timeStart, String timeExpire, String goodsTag, String notify_url,
                               String product_id) {

        // 微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(appid);

        // 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(mchid);

        // 要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
        setBody(body);

        setDetail(detail);

        // 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回，有助于商户自己可以注明该笔消费的具体内容，方便后续的运营和记录
        setAttach(attach);

        // 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);

        // 订单总金额，单位为“分”，只能整数
        setTotal_fee(totalFee);

        // 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
        setDevice_info(deviceInfo);

        // 订单生成的机器IP
        setSpbill_create_ip(spBillCreateIP);

        // 订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10
        // 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
        setTime_start(timeStart);

        // 订单失效时间，格式同上
        setTime_expire(timeExpire);

        // 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        setGoods_tag(goodsTag);

        // 随机字符串，不长于32 位
        setNonce_str(RandomUtil.randomStringUpper(32));

        // 根据API给的签名规则进行签名
        setNotify_url(notify_url);
        if (StringUtil.isEmpty(trade_type)) {
            setTrade_type("NATIVE");
        } else {
            setTrade_type(trade_type);
            if ("JSAPI".equals(trade_type))
                setOpenid(openid);
        }
        setProduct_id(product_id);

        String sign = Signature.getSign(toMap(), key);
        setSign(sign);// 把签名数据设置到Sign这个属性中
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if (obj != null) {
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
