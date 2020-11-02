package com.what.to.eat.server.web;

import lombok.Data;


public class WebContext {

    private static final ThreadLocal<PublicParameter> publicParameterHolder = new ThreadLocal<PublicParameter>() {
        @Override
        protected WebContext.PublicParameter initialValue() {
            return new WebContext.PublicParameter();
        }
    };

    /**
     * 获得公共参数
     */
    public static WebContext.PublicParameter getPublicParameter() {
        return publicParameterHolder.get();
    }

    /**
     * 清除
     */
    public static void clear() {
        publicParameterHolder.remove();
    }

    /**
     * 获取用户token
     */
    public static String getToken() {
        PublicParameter publicParameter = publicParameterHolder.get();
        return publicParameter.getToken();
    }

    /**
     * 获取用户id
     */
    public static int getUserId() {
        PublicParameter publicParameter = publicParameterHolder.get();
        return publicParameter.getUserId();
    }

    /**
     * 用户公共参数
     */
    @Data
    public static class PublicParameter {

        /**
         * 访问token,用户登陆成功后由系统颁发
         */
        private String token;

        /**
         * 用户id
         */
        private int userId;

    }

}
