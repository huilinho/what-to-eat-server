package com.what.to.eat.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 应用配置
 *
 * @author
 */
@Data
@Component
public class AppConfig {

    /**
     * 上传地址配置
     */
    @Value("${what-to-eat.upload-base-path}")
    private String uploadBasePath;

    @Value("${what-to-eat.upload-base-url}")
    private String uploadBaseUrl;

    /**
     * 环境
     */
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 解决因通过nginx做二级url映射后无法测试的问题
     */
    @Value("${swagger.host}")
    private String swaggerHost;


    @Value("${jwt.subject}")
    private String jwtSubject;

    @Value("${jwt.life}")
    private long jwtLife;

    @Value("${jwt.secret}")
    private String jwtSecret;
}
