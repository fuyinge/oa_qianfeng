package com.qianfeng.application.service;

import com.qianfeng.application.model.User;
import com.qianfeng.application.model.Users;

import java.util.Map;

public interface IUserService {
    Map<String,Object> queryUserPageMap(Map<String, Object> paramMap);

    void addUser(User user);

    Users getUserByUserId(int userId);

    void updateUser(User user);
}
