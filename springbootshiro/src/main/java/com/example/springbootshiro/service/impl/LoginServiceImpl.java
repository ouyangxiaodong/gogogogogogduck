package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.RoleRepository;
import com.example.springbootshiro.dao.UserRepository;
import com.example.springbootshiro.entity.Permission;
import com.example.springbootshiro.entity.Role;
import com.example.springbootshiro.entity.User;
import com.example.springbootshiro.service.ILoginService;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName: LoginServiceImpl
 * @Author: ouyangxiaodong
 * @Date: 2019/4/12 16:12
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User addUser(Map<String, Object> map) {
        User user = new User();
        user.setName(map.get("username").toString());
        user.setPassword(Integer.parseInt(map.get("password").toString()));
        userRepository.save(user);
        return user;
    }

    @Override
    public Role addRole(Map<String, Object> map) {

       User user = userRepository.findById(Long.parseLong(map.get("userId").toString())).get();
       Role role = new Role();
       role.setRoleName(map.get("roleName").toString());
       role.setUser(user);
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);

        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission1.setRole(role);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return role;
    }

    @Override
    public User findName(String name) {
        return userRepository.findByName(name);
    }
}
