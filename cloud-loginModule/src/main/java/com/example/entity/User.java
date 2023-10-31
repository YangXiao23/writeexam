package com.example.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: yangxiao
 * @DATE 2023/10/24 13:20
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;

    private List<UserRole> userRoles;
}
