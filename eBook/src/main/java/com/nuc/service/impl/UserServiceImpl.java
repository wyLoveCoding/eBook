package com.nuc.service.impl;

import com.nuc.mapper.UserMapper;
import com.nuc.pojo.User;
import com.nuc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.selByUser(user);
    }

    @Override
    public boolean regist(User user) {
        return userMapper.insUser(user);
    }

    @Override
    public User getUser(User user) {
        return userMapper.selUserById(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updUser(user);
    }

    @Override
    public boolean addMoney(User user, float money) {
        return userMapper.updMoneyById(user, money);
    }

    @Override
    public boolean exist(String username) {
        return userMapper.selByUserName(username);
    }
}
