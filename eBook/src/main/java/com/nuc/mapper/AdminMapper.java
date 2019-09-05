package com.nuc.mapper;

import com.nuc.pojo.Admin;
import com.nuc.pojo.User;

import java.util.List;

public interface AdminMapper {
    /**
     * 管理员功能描述
     */

    //管理员登录功能
     Admin selByAdmin(Admin admin);

    //个人信息查看
     Admin selAdminById(Admin admin);

    //个人信息修改
     Admin updAdmin(Admin admin);

    //查看所有用户
     List<User> selAllUsers();

    //得到总用户数
     int selCountOfUsers();

     //根据用户名查询用户
    List<User> selByUserName(String username);

    //删除用户
     boolean delUser(User user);

     //添加用户
    boolean insUser(User user);

//    public boolean testPwd(String adminname, String prepwd);
}
