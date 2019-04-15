package com.example.springbootshiro.dao;

import com.example.springbootshiro.entity.User;

/**
 * @Description: TODO
 * @ClassName: UserRepository
 * @Author: ouyangxiaodong
 * @Date: 2019/4/12 16:02
 */
public interface UserRepository extends BaseRepository<User,Long>{
    User findByName(String name);
}
