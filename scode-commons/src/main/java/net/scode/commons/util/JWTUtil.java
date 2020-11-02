package net.scode.commons.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JWTUtil {
    private JWTUtil() {
    }

    /**
     * 由字符串生成加密 key
     *
     * @return
     */
    private static SecretKey generalKey(String secret) {
        byte[] encodedKey = Base64.decodeBase64(secret);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }


    /**
     * 创建 jwt
     *
     * @param claims    私有信息
     * @param subject   jwt 的主题
     * @param secret    jwt的密钥
     * @param ttlMillis jwt 的有效期
     * @return 生成的 jwt
     */
    public static String createJWT(Map<String, Object> claims, String subject, String secret, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey(secret);
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密 jwt
     *
     * @param secret jwt的密钥
     * @param jwt    要被解析的 Json Web Token
     * @return 若解析成功，则返回 Claims
     * @throws MalformedJwtException
     */
    public static Claims parseJWT(String secret, String jwt) throws MalformedJwtException {
        try {
            SecretKey key = generalKey(secret);
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            log.info("parse token error, e={}", e.getMessage());
            return null;
        }

    }

    public static boolean isExpired(Claims claims, String subject) {
        if (claims == null) {
            return true;
        }
        Date date = new Date();
        if (!claims.getSubject().equals(subject)
                || claims.getExpiration().before(date)) {
            return true;
        }
        return false;
    }
}
