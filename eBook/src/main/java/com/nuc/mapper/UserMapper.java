package com.nuc.mapper;

import com.nuc.pojo.User;

public interface UserMapper {
    /**
     * 用户功能描述
     */

    //用户登录功能
    //SELECT * FROM user WHERE username = ? AND password = ?
     User selByUser(User user);

    //用户注册
    //INSERT INTO user (username,password,email,address,phone) VALUES (?,?,?,?,?)
     boolean insUser(User user);

    //个人信息查看
    //SELECT * FROM user WHERE id = ?
     User selUserById(User user);

    //个人信息修改
    //UPDATE user SET username=?, password=?, email=?, address=?, phone=? WHERE (id=?)
     int updUser(User user);

    //余额充值
    //UPDATE user SET money=? WHERE id=?
     boolean updMoneyById(User user, float money);

    //判断用户名是否存在
    //SELECT * FROM user WHERE username=?
     boolean selByUserName(String username);
}