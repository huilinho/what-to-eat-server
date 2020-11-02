package net.scode.commons.web;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import net.scode.commons.constant.Consts;
import net.scode.commons.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;

import static cn.hutool.core.convert.Convert.*;

/**
 * Request封装类
 *
 * @author tanghuang 2020年02月21日
 */
public class RequestUtil {

    /**
     * 获取客户端IP地址，此方法用在proxy环境中
     *
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtil.isEmpty(ip) || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtil.isEmpty(ip) || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        // 多级反向代理检测
        if (ip != null && ip.indexOf(",") > 0) {
            ip = ip.trim().split(",")[0];
        }
        if (!Validator.isIpv4(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 获得参数值
     *
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static String get(HttpServletRequest request, String name, String defaultValue) {
        return request.getParameter(name) != null ? request.getParameter(name) : defaultValue;
    }

    /**
     * 获得参数值
     *
     * @param request
     * @param name
     * @return
     */
    public static String get(HttpServletRequest request, String name) {
        return get(request, name, "");
    }

    /**
     * 获得int值
     *
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static int getInt(HttpServletRequest request, String name, int defaultValue) {
        return toInt(request.getParameter(name), defaultValue);
    }

    /**
     * 获得int值
     *
     * @param request
     * @param name
     * @return
     */
    public static int getInt(HttpServletRequest request, String name) {
        return getInt(request, name, 0);
    }

    /**
     * 获得float值
     *
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static float getFloat(HttpServletRequest request, String name, float defaultValue) {
        return toFloat(request.getParameter(name), defaultValue);
    }

    /**
     * 获得double值
     *
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static double getDouble(HttpServletRequest request, String name, double defaultValue) {
        return toDouble(request.getParameter(name), defaultValue);
    }

    /**
     * 获得long值
     *
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static long getLong(HttpServletRequest request, String name, long defaultValue) {
        return toLong(request.getParameter(name), defaultValue);
    }

    /**
     * 获取参数值数组
     *
     * @param request
     * @param paramName
     * @return
     */
    public static String[] getValues(HttpServletRequest request, String paramName) {
        return request.getParameterValues(paramName);
    }

    /**
     * 获取参数的数字值组合成一个字符串
     *
     * @param request
     * @param paramName
     * @param separator 分隔符
     * @return
     */
    public static String getValues(HttpServletRequest request, String paramName, String separator) {
        String[] values = request.getParameterValues(paramName);
        return StringUtil.join(separator, true, values);
    }

    /**
     * 取得表单里面所有的参数/值<br>
     * 多个值的默认以逗号隔开保存
     *
     * @param request
     * @param notStart
     * @return
     */
    public static Map<String, Object> getParam(HttpServletRequest request, String notStart) {
        return getParam(request, notStart, Consts.COMMA);
    }

    /**
     * 取得表单里面所有的参数/值<br>
     * 多个值的默认以符号隔开保存
     *
     * @param request
     * @param notStart
     * @param separator
     * @return
     */
    public static Map<String, Object> getParam(HttpServletRequest request, String notStart, String separator) {
        Map<String, Object> map = MapUtil.newHashMap();
        Enumeration<String> paramNames = request.getParameterNames();
        if (paramNames == null) {
            return map;
        }
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement().toString();
            if (!paramName.startsWith(notStart)) {
                String[] paramValues = request.getParameterValues(paramName);
                String paramValue = StringUtil.join(separator, true, paramValues);
                map.put(paramName, paramValue);
            }
        }
        return map;
    }

    /**
     * 取得http请求头所有参数/值
     *
     * @param request
     * @return
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = MapUtil.newHashMap();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration == null) {
            return map;
        }
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取HTTP端口
     *
     * @param req
     * @return
     */
    public static int getHttpPort(HttpServletRequest req) {
        try {
            return new URL(req.getRequestURL().toString()).getPort();
        } catch (Exception e) {
            return 80;
        }
    }

    /**
     * 获得请求的绝对路径(不带参数)<br>
     * 如：http://riji.163.com/weblog/abc.do
     *
     * @param request
     * @return
     */
    public static String getUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    /**
     * 获得请求的相对路径(不带参数)<br>
     * 如：/weblog/abc.do
     *
     * @param request
     * @return
     */
    public static String getPath(HttpServletRequest request) {
        return request.getRequestURI();
    }

    /**
     * 获得请求的参数
     *
     * @param request
     * @return
     */
    public static String getQuery(HttpServletRequest request) {
        return request.getQueryString() != null ? request.getQueryString() : "";
    }

    /**
     * 获得页面来源（前一页面）
     *
     * @param request
     * @return
     */
    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }

}
