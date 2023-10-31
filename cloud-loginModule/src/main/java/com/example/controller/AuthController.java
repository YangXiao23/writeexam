package com.example.controller;

import com.example.common.CommonResponse;
import com.example.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @author: yangxiao
 * @DATE 2023/10/25 0:06
 */
@RestController
@RequestMapping("oss/sql")
public class AuthController {

    @Autowired
    private DaoAuthenticationProvider authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping("/login")
    public CommonResponse login(String userName, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // 将Authentication对象存储到session中
        //HttpSession session = request.getSession();
        //session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, new SecurityContextImpl(authenticate));
        String token = jwtUtil.createAccessToken(authenticate);
        Map<String, String> map = Collections.singletonMap("toekn", token);
        return new CommonResponse(2000, "成功", map);
    }
}
