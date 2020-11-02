package com.what.to.eat.server.configure;

import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Charsets;
import net.scode.commons.web.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * web配置
 *
 * @author
 */
@Slf4j
@Configuration
public class WebConfiguration {

    /**
     * 注册过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(utf8CharacterEncodingFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * 跨域支持
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    /**
     * XSS过滤
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.setName("xssFilter");
        return registration;
    }

    /**
     * 统一编码utf-8过滤器<br>
     * characterEncodingFilter已在SpringBoot的HttpEncodingAutoConfiguration中唯一定义
     *
     * @return
     */
    private CharacterEncodingFilter utf8CharacterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(Charsets.UTF_8);
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

}
