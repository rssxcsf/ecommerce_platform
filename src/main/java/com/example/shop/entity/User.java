package com.example.shop.entity;

import lombok.Data;

@Data
public class User {
    // 主键ID
    private Integer id;
    // 用户名（登录用）
    private String username;
    // 密码
    private String password;
    // 真实姓名
    private String name;
    // 头像地址（可选）
    private String avatar;
    // 角色
    private String role;
    // 手机号（可选）
    private String phone;
    // 邮箱（可选）
    private String email;
}