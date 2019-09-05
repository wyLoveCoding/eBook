package com.nuc.service.impl;

import com.nuc.mapper.AdminMapper;
import com.nuc.pojo.Admin;
import com.nuc.pojo.User;
import com.nuc.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * 管理员登陆
     */
    @Override
    public Admin login(Admin admin) {
        return adminMapper.selByAdmin(admin);
    }

    /**
     * 根据ID获取管理员信息
     */
    @Override
    public Admin getAdmin(Admin admin) {
        return adminMapper.selAdminById(admin);
    }

    /**
     * 修改管理员个人信息
     */
    @Override
    public Admin updateAdmin(Admin admin) {
        return adminMapper.updAdmin(admin);
    }

    /**
     * 查询所有用户信息
     */
    @Override
    public List<User> listUser() {
        return adminMapper.selAllUsers();
    }

    /**
     * 获得总用户数量
     * @return
     */
    public int userCount(){
        return adminMapper.selCountOfUsers();
    }

    /**
     * 删除用户
     */
    @Override
    public boolean deleteUser(User user) {
        return adminMapper.delUser(user);
    }

    /**
     * 根据用户名查询用户
     * @param user
     * @return
     */
    @Override
    public List<User> searchUser(String username) {
        return adminMapper.selByUserName(username);
    }


    /**
     * 管理员添加用户
     */
    @Override
    public boolean addUser(User user) {
//        System.out.println("" + user);
        boolean result = adminMapper.insUser(user);
        System.out.println("添加用户功能执行结果:" + result + "-----------");
        return adminMapper.insUser(user);
    }

//    @Override
//    public boolean testPwd(String adminname, String prepwd) {
//        return adminMapper.testPwd(adminname, prepwd);
//    }
}
