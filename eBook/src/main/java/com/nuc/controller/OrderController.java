package com.nuc.controller;

import com.nuc.pojo.Order;
import com.nuc.pojo.User;
import com.nuc.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("Order")
public class OrderController {

    @Resource
    private OrderService orderServiceImpl;


//    /**
//     * @description             二级关联---先取出一级标签
//     */
//    @RequestMapping("/style")
//    public void style(HttpServletResponse response){
//        PrintWriter out= null;
//        try {
//            out = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String str = JSON.toJSONString(Date.MAP);
//        out.println(str);
//        out.flush();
//        out.close();
//    }

    /**
     * @description             二级关联---取出二级标签
     */
//    @RequestMapping("/category")
//    public void category(HttpServletRequest request,HttpServletResponse response){
//        String json = request.getParameter("field");
//        List<Style> list = Date.MAP_TWO.get(json);
//        for (Style style:list){
//            System.out.println(style);
//        }
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out= null;
//        try {
//            out = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String str = JSON.toJSONString(list);
//        out.println(str);
//        out.flush();
//        out.close();
//
//    }


    /**
     * 根据用户查询所有订单
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("user");
        List<Order> list = orderServiceImpl.listUserOrder(user);

        for(Order order1:list){
            order1.setUser(user);
            String name = order1.getBook().getBookimg().substring(2,order1.getBook().getBookimg().length());
            order1.getBook().setBookimg(name);
        }
        model.addAttribute("orderlist",list);
        return "order/order-list";
    }

    /**
     *  查看所有用户的订单
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("showAllusersorder")
    public String showAllusersorder(HttpServletRequest request, Model model){
        List<Order> orderList = orderServiceImpl.listOrder();
        System.out.println("\n\n------------------------所有订单orderController----------------------------------\n\n");
        System.out.println(orderList);
        System.out.println("\n\n------------------------所有订单orderController----------------------------------\n\n");
        for(Order order1:orderList){
            /*图片名去点*/
            String name = order1.getBook().getBookimg().substring(2,order1.getBook().getBookimg().length());
            order1.getBook().setBookimg(name);
        }
        model.addAttribute("orderList",orderList);
        return "order/order-list";
    }

    /**
     * 修改订单
     * @param order
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("updateorder")
    public String updateorder(Order order,HttpServletRequest request,Model model){
        System.out.println(order);
        //User user = (User) request.getSession().getAttribute("user");
        if (orderServiceImpl.updateOrder(order/*,user*/)){
            model.addAttribute("massage","修改成功！");
        }else {
            model.addAttribute("message", "修改失败！");
        }
        return "/Order/showAllusersorder";
    }

    /**
     * 删除订单
     * @param
     * @param request
     * @param model
     * @return
     */

    @RequestMapping("deleteOrder")
    @ResponseBody
    public Object deleteOrder(@RequestParam int id, HttpServletRequest request, Model model){
        System.out.println("进入");
        Order order = new Order();
        order.setOrderId(id);
        int result = 0;
        if (orderServiceImpl.deleteOrder(order)){
            result = 1;
            System.out.println("删除成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        }else {
            System.out.println("删除失败！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        }
        return result;
    }

    /**
     * 跳转到详情页面
     * @param order
     * @param model
     * @return
     */
    @RequestMapping("/orderInfo")
    public String orderInfo(Order order,Model model){
        System.out.println(order);
        order = orderServiceImpl.selectOrder(order);
        model.addAttribute("orderup",order);
        System.out.println(order);
        return "order/order-info";
    }

    @RequestMapping("/orderUpdate")
    public String orderUpdate(Order order,Model model){
        System.out.println(order);
        order = orderServiceImpl.selectOrder(order);
        model.addAttribute("orderup",order);
        System.out.println(order);
        return "order/order-update";
    }

}
