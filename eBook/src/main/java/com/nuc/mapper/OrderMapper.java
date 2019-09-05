package com.nuc.mapper;

import com.nuc.pojo.Order;
import com.nuc.pojo.Style;
import com.nuc.pojo.User;

import java.util.List;

public interface OrderMapper {
     /**
     * 订单信息asdasdasdasd
     */

    //修改订单
    public boolean updateOrder(Order order, User uer);

    //删除订单
    public  boolean deleteOrder(Order order);

    //根据订单id查找信息
    public Order selectOrder(Order order);

    //查看个人订单
    public List<Order> listOrder(User user);

    //查看所有订单
    public List<Order> listOrder();

    //按个人查询所有订单总数
    public int sumOrder(Order order);

    //查询订单总数
    public int sumOrder();

    //查看所有类别
    public List<Style> listStyle();

    //添加类别
    public void addStyle(Style style);

    //查询当前订单金额
    public Order orderMoney(Order order);

    //修改订单信息
    public int updateOrder(Order order);

    //修改用户余额
    public int updateUserMoney(User user);
}
