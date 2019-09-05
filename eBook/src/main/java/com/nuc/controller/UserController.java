package com.nuc.controller;

import com.nuc.pojo.User;
import com.nuc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("User")
public class UserController {

    @Resource
    private UserService userServiceImpl;

    /**
     * @description         用户登录
     */
    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session){
        User login = userServiceImpl.login(user);
        if (login == null){
            return "system/login";
        }
        else {
            model.addAttribute("message", "登陆成功");
            session.setAttribute("user", login);
            System.out.println("登陆成功");
            System.out.println("user:--------"+login);
            return "system/menu";
        }
    }

    /**
     * @description         用户注册
     */
    @RequestMapping("/regist")
    public String regist(User user, Model model){
        if (userServiceImpl.regist(user)){
            model.addAttribute("message","注册成功");
            return "user/login";
        }else {
            model.addAttribute("message","注册失败");
            return "system/login";
        }
    }

    /**
     * @description         展示个人信息
     */
    @RequestMapping("/show")
    public String show(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        userServiceImpl.getUser(user);
        model.addAttribute("user",user);
        return "user/personalInfo";
    }

    /**
     * @description         修改个人信息
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        int result = userServiceImpl.updateUser(user);
        if (result >= 1)
            request.setAttribute("message","修改失败");
        else{
            request.setAttribute("message","修改成功");
            request.setAttribute("user",user);
        }
        return "user/personalInfo";
    }

    /**
     * @description         余额充值
     */
    @RequestMapping("/addMoney")
    public String addMoney(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        float money = Float.parseFloat(request.getParameter("total_amount"));
        if (userServiceImpl.addMoney(user,money))
        {
            request.setAttribute("message","充值成功");
            request.setAttribute("user",user);
        }
        else {
            request.setAttribute("message", "充值失败");
        }
        return "user/personalInfo";
    }

    /**
     * @description         前台校验
     */
    @RequestMapping("/exist")
    public void exist(String username,HttpServletResponse response){
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean exists = userServiceImpl.exist(username);
        response.setContentType("text/html;charset=utf-8");
        if (exists){
            printWriter.print("true");
            printWriter.flush();
            printWriter.close();

        }else {
            printWriter.print("false");
            printWriter.flush();
            printWriter.close();
        }
    }

}
