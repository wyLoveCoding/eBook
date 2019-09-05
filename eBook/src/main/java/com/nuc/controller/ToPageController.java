package com.nuc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ToPage")
public class ToPageController {

    @RequestMapping("/welcome")
    public String welcome(){
        return "admin/login";
    }

    @RequestMapping("/main")
    public String toMain(){
        return "system/menu";
    }

    @RequestMapping("userLogin")
    public  String userLogin(){
        return "user/login";
    }

    @RequestMapping("/regist")
    public String regist(){
        return "user/regist";
    }
}
