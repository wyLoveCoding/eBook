package com.nuc.controller;

import com.nuc.pojo.Admin;
import com.nuc.pojo.User;
import com.nuc.service.AdminService;
import com.nuc.service.OrderService;
import com.nuc.service.UserService;
import com.nuc.service.impl.OrderServiceImpl;
import com.nuc.service.impl.UserServiceImpl;
import com.nuc.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Admin")
public class AdminController {

    private List<User> userList = new ArrayList<>();
    private Admin admin = new Admin();


    @Resource
    private AdminService adminServiceImpl;
    @Resource
    private OrderService orderServiceImpl;
    @Resource
    private UserService userServiceImpl;


    /**
     * 管理员登陆
     */
    @RequestMapping("/login")
    public String login(String adminname, String adminpwd, HttpSession session){
        admin.setAdminName(adminname);
        admin.setAdminPwd(adminpwd);
        System.out.println("" + admin);
        Admin admin1 = adminServiceImpl.login(admin);
        if (admin1 == null)
        {
            System.out.println("失败Admin/login");
            session.setAttribute("message","登陆失败");
            return "user/login";
        }
        else
        {
            System.out.println("成功Admin/login");
            //存储二级标签信息
            Date.MAP = new ArrayList<>();
            Date.MAP_TWO = new HashMap<>();
            orderServiceImpl.listStyle();
            session.setAttribute("admin",admin1);
            return "redirect:/menu.jsp";
        }

    }

    /**
     * 根据ID获取管理员信息
     */
    @RequestMapping("/show")
    public String show(HttpServletRequest request){
        admin = (Admin) request.getSession().getAttribute("admin");
        Admin admin1 = adminServiceImpl.getAdmin(admin);
        System.out.println(admin1.getAdminName());
        return "admin/admin-info";
    }

    /**
     * 管理员根据用户名查询用户
     */
    @RequestMapping("/search")
    public String searchByName(String username, HttpServletRequest request){
        List<User> userList = adminServiceImpl.searchUser(username);
        request.setAttribute("userList", userList);
        userList.forEach(System.out::println);
        return "admin/user-list";
    }

    /**
     * 修改管理员个人信息
     */
    @RequestMapping("/update")
    public String update(String adminpwd, String repwd, String newpwd, HttpServletRequest request){
        admin = (Admin) request.getSession().getAttribute("admin");
        if (admin.getAdminPwd().equals(adminpwd)){
            if (repwd.equals(newpwd)){
                admin.setAdminPwd(request.getParameter("newpwd"));
                admin = adminServiceImpl.updateAdmin(admin);
                request.setAttribute("admin",admin);
            }
        }
        return "redirect:/index.jsp";
        }


    /**
     * 查询所有用户信息
     * 分页
     */
    @RequestMapping("/list")
    public String list(Model model){
        //Page page = new Page();
        userList = adminServiceImpl.listUser();
        int count = adminServiceImpl.userCount();

        for (User user:userList){
//            System.out.println(user);
        }
        model.addAttribute("userList",userList);
        model.addAttribute("count",count);
        model.addAttribute("flushFlag",true);
        return "admin/user-list";
    }

    /**
     * 删除用户
     */
    @RequestMapping("/deleteOne")
    public String deleteOne(User user){
        System.out.println(user);
        adminServiceImpl.deleteUser(user);
        return "admin/user-list";
    }
    /*
     *批量删除
     */
    @RequestMapping("/deleteMore")
    public String deleteMore(String ids, Map<String, Object> map){
        System.out.println("ids----------" + ids);
        String a[] = ids.split(",");
        User user = new User();
        for (int i = 0; i < a.length; i++){
            String id = a[i];
            user.setId(Integer.parseInt(id));
            adminServiceImpl.deleteUser(user);
            map.put("msg", 1);
        }


        return "admin/user-list";
    }

    /*@RequestMapping("/test")
    public String test(Admin admin){
        PrintWriter printWriter =  response.getWriter();

        //判断用户名密码是否正确
        boolean testPwd = service.testPwd(admin.getAdminName(), admin.getAdminPwd());

        if (testPwd){
            printWriter.print("true");
            printWriter.flush();
            printWriter.close();
        }else {
            printWriter.print("false");
            printWriter.flush();
            printWriter.close();
        }
    }*/

    /**
     * 跳转到添加用户页面
     */
    @RequestMapping("/add")
    public String addUser(){
        return "admin/user-add";
    }

    /**
     * 跳转到修改用页面
     */
    @RequestMapping("/updateuser")
    public String getUser(User user,Model model){

        user = userServiceImpl.getUser(user);
        model.addAttribute("user",user);
        return "admin/user-edit";
    }

    /**
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/update-user")
    public String updateUser(User user,Model model){
        int result = userServiceImpl.updateUser(user);
        if (result >= 1)
            model.addAttribute("message","修改失败");
        else{
            model.addAttribute("message","修改成功");
            model.addAttribute("user",user);
        }
        return "admin/user-list";
    }

    /**
     * 用户注册添加
     * @param user
     * @return
     */
    @RequestMapping("/adduser")
    public String add(User user, Model model){
//        System.out.println("" + user);
        boolean flag = adminServiceImpl.addUser(user);
//        if (flag) System.out.println("添加成功");
//        else System.out.println("添加失败");
        return "forward:list";
    }
}
