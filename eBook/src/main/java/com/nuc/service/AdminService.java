package com.nuc.service;

import com.nuc.pojo.Admin;
import com.nuc.pojo.User;

import java.util.List;

public interface AdminService {
    /**
     * 管理员功能描述
     */

    //管理员登录功能
     Admin login(Admin admin);

    //个人信息查看
     Admin getAdmin(Admin admin);

    //个人信息修改
     Admin updateAdmin(Admin admin);

    //查看所有用户
     List<User> listUser();

    //获得总用户数
     int userCount();

    //删除单个用户
     boolean deleteUser(User user);

     //根据用户名查询用户
     List<User> searchUser(String username);

     //添加用户
     boolean addUser(User user);

//     boolean testPwd(String adminname, String prepwd);
}
