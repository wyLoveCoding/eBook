package com.nuc.service.impl;

import com.nuc.mapper.BookMapper;
import com.nuc.mapper.OrderMapper;
import com.nuc.mapper.UserMapper;
import com.nuc.pojo.Book;
import com.nuc.pojo.Order;
import com.nuc.pojo.Style;
import com.nuc.pojo.User;
import com.nuc.service.OrderService;
import com.nuc.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;


    User user = new User();
    Book book = new Book();

    //修改订单
    @Override
    public boolean updateOrder(Order order/*, User user*/) {
        System.out.println(order);
        //修改后的金额
        float fina = order.getMoney();
        System.out.println("修改后的金额"+fina);
        //查询修改前订单金额
        float previous = orderMapper.orderMoney(order).getMoney();
        System.out.println("查询修改前订单金额"+previous);
        //修改前后差额
        float update = fina - previous;
        System.out.println("修改前后差额"+update);
        //修改订单金额数量
        int i = orderMapper.updateOrder(order);
        System.out.println("修改订单金额数量"+i);
        //修改用户余额
        user.setMoney(update);
        int j = orderMapper.updateUserMoney(user);
        System.out.println("修改用户余额"+j);

        if (i == 1&&j ==1){
            return true;
        }
        return false;
    }

    //删除订单
    @Override
    public boolean deleteOrder(Order order/*, User user*/) {
        System.out.println("deleteOrder服务实现层"+order);
        order = orderMapper.selectOrder(order);
        System.out.println("deleteOrder服务实现层查找后"+order);
        user = order.getUser();
        System.out.println("deleteOrder服务实现层查找后user信息"+user);
        boolean result = false;
        result = orderMapper.deleteOrder(order);
        return result;
    }

    //查看订单详情
    @Override
    public Order selectOrder(Order order) {
        System.out.println("123+"+order);
        order = orderMapper.selectOrder(order);
        System.out.println("456+"+order);
        user.setId(order.getUserId());
        book.setBookid(order.getBookId());
        order.setUser(userMapper.selByUser(user));
        order.setBook(bookMapper.selectBook(book));
        return order;

    }

    //查看所有订单
    @Override
    public List<Order> listOrder() {
        List<Order> orderList = new ArrayList<Order>();
        orderList = orderMapper.listOrder();
        System.out.println(orderList);

        for (Order order:orderList){
            user.setId(order.getUserId());
            book.setBookid(order.getBookId());
            order.setUser(userMapper.selByUser(user));
            order.setBook(bookMapper.selectBook(book));
        }
        return orderList;
    }

    //查看个人订单
    @Override
    public List<Order> listUserOrder(User user) {
        List<Order> orderList = new ArrayList<Order>();
        orderList = orderMapper.listOrder();

        return orderList;
    }

    //按个人查询所有订单总数
    @Override
    public int sumOrder(Order order) {
        int result = 0;
        result = orderMapper.sumOrder(order);
        return result;
    }

    //查询订单总数
    @Override
    public int sumOrder() {
        int result = 0;
        result = orderMapper.sumOrder();
        return result;
    }

    //查看所有类别
    @Override
    public List<Style> listStyle() {
//        List<Style> list = new ArrayList<>();
//        list = orderMapper.listStyle();
//        for (Style style:list) {
//            System.out.println(style);
//            if(style.getBookCategory().equals("1")){
//                Date.MAP.add(style);
//            }
//            else{
//                int index = style.getBookStyle().indexOf('_');
//                String name = style.getBookStyle().substring(0,index);
//                System.out.println(name+"@@@"+Date.MAP_TWO.containsKey(name));
//                if(Date.MAP_TWO.containsKey(name)){
//
//                    List<Style> list1_two = Date.MAP_TWO.get(name);
//                    //System.out.println(list1_two.size());
//                    list1_two.add(style);
//                }
//                else{
//                    List<Style> list1_two = new ArrayList<>();
//                    list1_two.add(style);
//                    // System.out.println(name+"   "+list1_two.size());
//                    Date.MAP_TWO.put(name,list1_two);
//                }
//            }
//        }
//
//        for (Map.Entry<String, List<Style>> entry : Date.MAP_TWO.entrySet()) {
//            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//            for (Style style: entry.getValue()) {
//                System.out.println(style);
//            }
//        }
//
//        for (Style style : Date.MAP) {
//            System.out.println(style);
//        }
//
//        return list;
        return null;
    }

    //添加类别
    @Override
    public void addStyle(Style style) {
        orderMapper.addStyle(style);
    }
}
