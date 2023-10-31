package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: yangxiao
 * @DATE 2023/10/24 13:18
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    User loadUserByName(String userName);
}
