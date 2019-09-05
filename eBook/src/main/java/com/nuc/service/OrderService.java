package com.nuc.service;

import com.nuc.pojo.Order;
import com.nuc.pojo.Style;
import com.nuc.pojo.User;

import java.util.List;

public interface OrderService {
    /**
     * 订单信息
     */


    //修改订单
    public boolean updateOrder(Order order/*, User user*/);

    //删除订单
    public boolean deleteOrder(Order order/*, User user*/);

    //根据订单id查找信息
    public Order selectOrder(Order order);

    //查看所有订单
    public List<Order> listOrder();

    //查看个人订单
    public List<Order> listUserOrder(User user);

    //按个人查询所有订单总数
    public int sumOrder(Order order);

    //查询订单总数
    public int sumOrder();

    //查看所有类别
    public List<Style> listStyle();

    //添加类别
    public void addStyle(Style style);
}