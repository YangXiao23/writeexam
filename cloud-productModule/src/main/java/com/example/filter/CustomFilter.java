package com.example.filter;

import com.example.entity.CommonResponse;
import com.example.util.JWTUtil;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义用户凭证解析过滤器
 *
 * @author: yangxiao
 * @DATE 2023/10/28 4:25
 */
@Component
public class CustomFilter  extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (!ObjectUtils.isEmpty(token)) {
            try {
                Claims claims = jwtUtil.Claims(token);
                String name = claims.get("name", String.class);
                List<String> list = claims.get("role", List.class);
                List<SimpleGrantedAuthority> authorities = list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(name, null, authorities));
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("token不正确或失效");
            }
        }
        filterChain.doFilter(request, response);
    }
}
