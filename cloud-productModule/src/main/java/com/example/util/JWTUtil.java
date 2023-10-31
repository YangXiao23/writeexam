package com.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: yangxiao
 * @DATE 2023/10/28 4:38
 */
@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;

    public Claims Claims(String token) {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(secret)
                .build().parseSignedClaims(token);
        return jwsClaims.getBody();
    }
}
