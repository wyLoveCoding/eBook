package com.nuc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Pay")
public class PayController {

    @RequestMapping("load")
    public String welcome(){
        return "/WEB-INF/views/user/login.jsp";
    }
}
