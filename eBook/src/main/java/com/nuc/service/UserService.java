package com.nuc.service;

import com.nuc.pojo.User;

public interface UserService {

    /**
     * 用户功能描述
     */

    //用户登录功能
     User login(User user);

    //用户注册
     boolean regist(User user);

    //个人信息查看
     User getUser(User user);

    //个人信息修改
     int updateUser(User user);

    //余额充值
     boolean addMoney(User user, float money);

    //判断用户名是否存在
     boolean exist(String username);
}
