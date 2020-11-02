package com.what.to.eat.server.web;

import cn.hutool.json.JSONUtil;
import com.what.to.eat.server.config.AppConfig;
import com.what.to.eat.server.consts.AuthConsts;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import net.scode.commons.util.JWTUtil;
import net.scode.commons.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 管理后台业务相关拦截器
 *
 * @author tanghuang 2020年02月25日
 */
@Slf4j
public class WebServiceInterceptor implements HandlerInterceptor {
    private static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";

    private List<String> excludePatterns = new ArrayList<>(
            Arrays.asList(
                    "/api/user/code2session", "/api/user/login", "/api/planeInfo/list",
                    "/api/city/**",
                    "/api/wx/**",
                    "/api/journal/*",
                    "/api/airport/list",
                    "/api/special_flight/**",
                    "/api/member_grade/list",
                    "/api/channelPeriod/list",
                    "/api/trip/list", "/api/trip/detail",
                    "/api/article/list", "/api/article/detail",
                    "/api/banner/list",
                    "/api/schedule/**",
                    "/api/activity/**",
                    "/api/investment/list", "/api/investment/findById"));

    private PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private AppConfig appConfig;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param request
     * @param response
     * @param handler
     * @return true，如果false，停止流程，api被拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        String uri = request.getRequestURI();
        boolean ignore = shouldIgnore(uri);

        log.debug("request=>uri:{}, ignore:{}", uri, ignore);
        // 填充公共参数
        String bearToken = request.getHeader(AuthConsts.AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(bearToken)) {
            bearToken = request.getParameter("token");
            log.info("header 中token 为空，尝试从request中获取:{}", bearToken);
        }
        WebContext.PublicParameter publicParameter = WebContext.getPublicParameter();
        publicParameter.setToken(bearToken);

        if (!ignore && StringUtil.isEmpty(bearToken)) {
            setResponse(response, R.error(Consts.NO_LOGIN_CODE, "未登录"));
            return false;
        }
        // 检查token
        Claims claims = JWTUtil.parseJWT(appConfig.getJwtSecret(), bearToken);
        if (!ignore && JWTUtil.isExpired(claims, appConfig.getJwtSubject())) {
            setResponse(response, R.error(Consts.NO_LOGIN_CODE, "未登录"));
            return false;
        }
        if (claims != null) {
            int userId = Integer.parseInt(String.valueOf(claims.get("userId")));
            publicParameter.setUserId(userId);
        }

        // 检查token
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
        }

        return true;
    }

    private boolean shouldIgnore(String uri) {
        if (!ObjectUtils.isEmpty(this.excludePatterns)) {
            for (String pattern : this.excludePatterns) {
                if (pathMatcher.match(pattern, uri)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void setResponse(ServletResponse response, R result) {
        try {
            OutputStream os = response.getOutputStream();
            String jsonStr = JSONUtil.toJsonStr(result);
            os.write(jsonStr.getBytes(StandardCharsets.UTF_8));
            response.setContentType(JSON_CONTENT_TYPE);
        } catch (Exception ex) {
            log.warn("exception=" + ex);
        }
    }
}
