package com.example.application.service;

import com.example.application.mapper.UserMapper;
import com.example.application.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName (String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public void userRegister(User user) {

    }

    /**
     * 根据用户名查询这个用户的盐值
     *
     * @param name
     * @return
     */
    @Override
    public String selectAsaltByName(String name)
    {
        return userMapper.selectAsaltByName(name);
    }
}
