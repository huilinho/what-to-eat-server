package net.scode.commons.web;

import cn.hutool.core.lang.Validator;
import net.scode.commons.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie处理类
 *
 * @author tanghuang 2020年02月21日
 */
public class CookieUtil {

    /**
     * 添加cookie
     *
     * @param response
     * @param name     cookie的key
     * @param value    cookie的value
     * @param domain   domain
     * @param path     path
     * @param maxage   最长存活时间 单位为秒
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String domain, String path, int maxage) {
        if (StringUtil.isEmpty(name)) {
            return;
        }
        if (value == null) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(maxage);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 添加cookie
     *
     * @param request
     * @param response
     * @param name         cookie的key
     * @param value        cookie的value
     * @param path         path
     * @param maxage       最长存活时间 单位为秒
     * @param allSubDomain
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, String path, int maxage,
                                 boolean allSubDomain) {
        if (StringUtil.isEmpty(name)) {
            return;
        }
        if (value == null) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        if (allSubDomain) {
            String serverName = request.getServerName();
            String domain = getDomainOfServerName(serverName);
            if (domain != null && domain.indexOf('.') != -1) {
                cookie.setDomain('.' + domain);
            }
        }
        cookie.setMaxAge(maxage);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 往根下面存一个cookie
     *
     * @param response
     * @param name     cookie的key
     * @param value    cookie的value
     * @param domain   domain
     * @param maxage   最长存活时间 单位为秒
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String domain, int maxage) {
        addCookie(response, name, value, domain, "/", maxage);
    }

    /**
     * 设置cookie，关闭浏览器就失效
     *
     * @param response
     * @param name     cookie的key
     * @param value    cookie的value
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response, name, value, null, "/", -1);
    }

    /**
     * 从cookie值返回cookie值，如果没有返回 null
     *
     * @param request
     * @param name
     * @return cookie的值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equalsIgnoreCase(name)) {
                return cookies[i].getValue();
            }
        }
        return null;
    }

    /**
     * 删除cookie
     *
     * @param request
     * @param response
     * @param name
     * @param domain
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        String cookieVal = getCookie(request, name);
        if (cookieVal != null) {
            CookieUtil.addCookie(response, name, null, domain, 0);
        }
    }

    /**
     * 删除cookie
     *
     * @param request
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        CookieUtil.removeCookie(request, response, name, "");
    }

    /**
     * 获取用户访问URL中的根域名
     * 例如: www.scode.net -> scode.net
     *
     * @param host
     * @return
     */
    public static String getDomainOfServerName(String host) {
        if (Validator.isIpv4(host)) {
            return null;
        }
        String[] names = host.split("\\.");
        int len = names.length;
        if (len == 1) {
            return null;
        } else if (len == 3) {
            return StringUtil.join(".", true, names[len - 2], names[len - 1]);
        } else if (len > 3) {
            String dp = names[len - 2];
            if (dp.equalsIgnoreCase("com") || dp.equalsIgnoreCase("net") || dp.equalsIgnoreCase("cn") || dp.equalsIgnoreCase("xyz")) {
                return StringUtil.join(".", true, names[len - 3], names[len - 2], names[len - 1]);
            } else {
                return StringUtil.join(".", true, names[len - 2], names[len - 1]);
            }
        }
        return host;
    }

}
