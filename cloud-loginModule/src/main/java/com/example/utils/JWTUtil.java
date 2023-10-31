package com.example.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: yangxiao
 * @DATE 2023/10/23 8:58
 */

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;
    public  String createAccessToken(OAuth2User oAuth2User) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) oAuth2User.getAuthorities();
        Object id = oAuth2User.getAttribute("id");
        Object name = oAuth2User.getAttribute("login");
        Map<String, Object> map = new HashMap<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_EDITOR");
        List<String> roles = authorities.stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
        roles.add(authority.getAuthority());
        map.put("userName", name);
        map.put("role", roles);
        return Jwts.builder().setClaims(map).setHeaderParam("typ", "JWT").setExpiration(generateExpirationDate())
                //签名算法
                .signWith(SignatureAlgorithm.HS512, secret).compact();

    }



    public  String createAccessToken(Authentication user) {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        Object name = user.getName();
        List<String> roles = authorities.stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("userName", name);
        map.put("role", roles);
        return Jwts.builder().setClaims(map).setHeaderParam("typ", "JWT").setExpiration(generateExpirationDate())
                //签名算法
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration);
    }



    public Claims Claims(String token) {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(secret)
                .build().parseSignedClaims(token);
        return jwsClaims.getBody();
    }
}
