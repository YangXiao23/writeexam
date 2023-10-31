package com.example.controller;

import com.example.common.CommonResponse;
import com.example.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.DirContext;
import java.util.Collections;
import java.util.Map;

/**
 * @author: yangxiao
 * @DATE 2023/10/25 7:23
 */
@RestController
@RequestMapping("oss/ladp")
public class LDAPAuthController {

    @Autowired
    private LdapAuthenticationProvider ldapAuthenticationProvider;

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private LdapContextSource contextSource;

    @PostMapping ("/login")
    public CommonResponse login(String userName, String password) {
        DirContext readOnlyContext = contextSource.getReadOnlyContext();

        Authentication authentication = ldapAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        if (ObjectUtils.isEmpty(authentication)) {
            return new CommonResponse(200, "", "登录失败");
        }
        String token = jwtUtil.createAccessToken(authentication);
        Map<String, String> map = Collections.singletonMap("toekn", token);
        return new CommonResponse(200, "登录成功", map);
    }
}
