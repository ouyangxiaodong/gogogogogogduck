package com.example.springbootshiro.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @Description: 用户
 * @ClassName: User
 * @Author: ouyangxiaodong
 * @Date: 2019/4/12 14:58
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer password;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
