package com.example.springbootshiro;

import com.example.springbootshiro.entity.Role;
import com.example.springbootshiro.entity.User;
import com.example.springbootshiro.service.ILoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.shiro.subject.Subject;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName: LoginResource
 * @Author: ouyangxiaodong
 * @Date: 2019/4/12 16:52
 */
@RestController
public class LoginResource {
    @Autowired
    private ILoginService longService;
    //退出的时候是get请求，主要是用于退出
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "login")
    public String login(@RequestBody Map<String,Object> map){
        // 添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(map.get("username").toString(),map.get("password").toString());

        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException ex) {
            return "用户名没有找到";
        } catch (IncorrectCredentialsException ex) {
            return "用户名密码不匹配";
        }catch (AuthenticationException e) {
            return "其他的登录错误";
        }
        return "login";
    }
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        return "logout";
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }
    //数据初始化
    @RequestMapping(value = "/addUser")
    public String addUser(@RequestBody Map<String,Object> map){
        User user = longService.addUser(map);
        return "addUser is ok! \n" + user;
    }
    //角色初始化
    @RequestMapping(value = "/addRole")
    public String addRole(@RequestBody Map<String,Object> map){
        Role role = longService.addRole(map);
        return "addRole is ok! \n" + role;
    }
    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create(){
        return "Create success!";
    }

}


