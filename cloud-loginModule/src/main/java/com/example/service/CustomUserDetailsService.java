package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yangxiao
 * @DATE 2023/10/24 13:32
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       if (StringUtils.isNullOrEmpty(username)) {
           throw new UsernameNotFoundException("用户名为空");
       }
       User user = userMapper.loadUserByName(username);
       if (user == null) {
            throw new UsernameNotFoundException("User not found");
       }
        System.out.println(user);
        // Convert user's roles to a list of GrantedAuthority
        List<GrantedAuthority> authorities = user.getUserRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), authorities);
    }
}
