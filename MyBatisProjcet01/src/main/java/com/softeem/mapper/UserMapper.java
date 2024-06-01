package com.softeem.mapper;

import com.softeem.bean.User;

import java.util.List;

public interface UserMapper {

    /**
    * 添加用户信息
    */
    int insertUser();

    int deleteUser();

    int updateUser();

    User getUserById();

    List<User> getUserList();
}