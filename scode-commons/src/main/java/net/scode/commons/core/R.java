package net.scode.commons.core;

import net.scode.commons.constant.Consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果类
 *
 * @author tanghuang 2020年02月18日
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = -8157613083634272196L;

    /**
     * 默认构造器，成功
     */
    public R() {
        put("code", Consts.SUCCESS_CODE);
        put("msg", "success");
    }

    public static R error() {
        return error(Consts.ERROR_CODE, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(Consts.ERROR_CODE, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R data(Object obj) {
        R r = new R();
        r.put("data", obj);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
