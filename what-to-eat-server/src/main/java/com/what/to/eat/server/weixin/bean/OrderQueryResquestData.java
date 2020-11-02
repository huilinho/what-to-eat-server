package com.what.to.eat.server.weixin.bean;

import com.what.to.eat.server.weixin.util.Signature;
import lombok.Data;
import net.scode.commons.util.StringUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/*
 * 订单查询数据
 */
@Data
public class OrderQueryResquestData {
    // 每个字段具体的意思请查看API文档
    private String appid;
    private String mch_id;
    private String transaction_id;
    private String out_trade_no;
    private String nonce_str;
    private String sign;

    public OrderQueryResquestData(String appid, String mchid, String key, String transaction_id, String out_trade_no, String nonce_str) {

        // 微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(appid);

        // 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(mchid);

        if (!StringUtil.isEmpty(transaction_id)) {
            setTransaction_id(transaction_id);
        }

        if (!StringUtil.isEmpty(out_trade_no)) {
            setOut_trade_no(out_trade_no);
        }

        setNonce_str(nonce_str);

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
