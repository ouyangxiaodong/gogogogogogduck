package com.example.springbootshiro.service;

import com.example.springbootshiro.entity.Role;
import com.example.springbootshiro.entity.User;

import java.util.Map;

/**
 * @auther: ouyangxiaodong
 * @Description 用户登录处理service
 * @date: 2019/4/12 16:08
 * @param: 
 * @return: 
 */
public interface ILoginService {

    User addUser(Map<String,Object> map);
    Role addRole(Map<String,Object> map);
    User findName(String name);
}
