package com.what.to.eat.server.dto;

import lombok.Data;

/**
 * @author huilin
 * @version 1.0
 * @date 2020/11/7 22:42
 */
@Data
public class LoginDTO {
    private String openid;
    private String rawData;
    private String signature;
    private String encryptedData;
    private String iv;
}
