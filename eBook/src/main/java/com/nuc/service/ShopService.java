package com.nuc.service;

import com.nuc.pojo.Book;
import com.nuc.pojo.Shop;
import com.nuc.pojo.User;

import java.util.List;

public interface ShopService {

    //购物车功能
    //查看所有购物信息123123123123
     List<Shop> listShop(User user);

    //用户添加商品到购物车
     boolean addShop(Book book, User user);

    //用户删除购物车商品
     boolean deleteShop(Shop shop);

    //用户修改购物车商品
     boolean updateShop(Shop shop);

    //用户结算购物车
     boolean sumMoney(User user, String ids[]);
}
