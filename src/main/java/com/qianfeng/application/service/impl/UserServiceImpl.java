package com.qianfeng.application.service.impl;

import com.qianfeng.application.dao.UserMapper;
import com.qianfeng.application.model.User;
import com.qianfeng.application.model.Users;
import com.qianfeng.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
  @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, Object> queryUserPageMap(Map<String, Object> paramMap) {

        int startIndex=Integer.parseInt(paramMap.get("startIndex").toString());
        int pageSize=Integer.parseInt(paramMap.get("pageSize").toString());
        paramMap.put("startIndex",startIndex);
        paramMap.put("pageSize",pageSize);
        List<User> userList=userMapper.getUserList(paramMap);
        int total=userMapper.getUserTotal(paramMap);
        Map<String, Object> resultMap=new HashMap<String,Object>();
        resultMap.put("userList",userList);
        resultMap.put("total",total);

        return resultMap;
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public Users getUserByUserId(int userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
