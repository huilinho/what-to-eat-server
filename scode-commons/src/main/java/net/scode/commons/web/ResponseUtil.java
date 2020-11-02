package net.scode.commons.web;

import cn.hutool.json.JSONUtil;
import net.scode.commons.core.R;
import net.scode.commons.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Response封装类
 *
 * @author tanghuang 2020年02月21日
 */
public class ResponseUtil {

    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 设置允许跨域访问
     *
     * @param request
     * @param response
     */
    public static void setAllowOrigin(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
    }

    /**
     * 设置页面无缓存
     *
     * @param response
     */
    public static void setNoCache(HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 设置html页面头
     *
     * @param response
     */
    public static void setHtmlHeader(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * 页面重定向
     *
     * @param response
     * @param url
     * @return
     */
    public static void sendRedirect(HttpServletResponse response, String url) {
        if (response == null || StringUtil.isEmpty(url)) {
            return;
        }

        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 页面输出
     *
     * @param response
     * @param content
     */
    public static void write(HttpServletResponse response, String content) {
        if (response == null || StringUtil.isEmpty(content)) {
            return;
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(content);
            out.flush();
        } catch (Exception e) {
            log.error("错误", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception exc) {
                }
            }
        }
        return;
    }

    /**
     * 输出文本/html
     *
     * @param response
     * @param text
     */
    public static void renderText(HttpServletResponse response, String text) {
        ResponseUtil.setNoCache(response);

        setHtmlHeader(response);
        write(response, text);
    }

    /**
     * 输出xml
     *
     * @param response
     * @param xml
     */
    public static void renderXml(HttpServletResponse response, String xml) {
        ResponseUtil.setNoCache(response);

        response.setContentType("text/xml;charset=UTF-8");
        write(response, xml);
    }

    /**
     * 输出json
     *
     * @param response
     * @param json
     * @return
     */
    public static void renderJson(HttpServletResponse response, String json) {
        ResponseUtil.setNoCache(response);

        response.setContentType("application/json;charset=UTF-8");
        write(response, json);
    }

    /**
     * 输出result
     *
     * @param response
     * @param result
     * @return
     */
    public static void renderResponse(HttpServletResponse response, R result) {
        renderJson(response, JSONUtil.toJsonStr(result));
    }

    /**
     * 输出流
     *
     * @param response
     * @param bytes
     * @return
     */
    public static void renderStream(HttpServletResponse response, byte[] bytes) {
        // 这里不加的话，AJAX会缓存每次取的数据，从而造成数据不能更新
        ResponseUtil.setNoCache(response);

        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        } catch (Exception e) {
            log.error("错误", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception exc) {
                }
            }
        }
    }

    /**
     * 输出png图片
     *
     * @param response
     * @param bytes
     */
    public static void renderPng(HttpServletResponse response, byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            renderPng(response, ImageIO.read(bais));
        } catch (Exception e) {
            log.error("错误", e);
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (Exception exc) {
                }
            }
        }
    }

    /**
     * 输出png图片
     *
     * @param response
     * @param bufferedImage
     */
    public static void renderPng(HttpServletResponse response, BufferedImage bufferedImage) {
        ServletOutputStream out = null;
        try {
            ResponseUtil.setNoCache(response);
            response.setHeader("Content-Type", "image/png");
            response.setContentType("image/png");
            out = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", out);
            out.flush();
        } catch (Exception e) {
            log.error("错误", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception exc) {
                }
            }
        }
    }

    /**
     * 输出一段js代码，包含script标签
     *
     * @param response
     * @param jscode
     * @throws Exception
     */
    public static void renderScript(HttpServletResponse response, String jscode) {
        String text = StringUtil.concat("<script>", jscode, "</script>");
        renderText(response, text);
    }

}
