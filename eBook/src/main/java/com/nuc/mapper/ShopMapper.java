package com.nuc.mapper;

import com.nuc.pojo.Book;
import com.nuc.pojo.Shop;
import com.nuc.pojo.User;

import java.util.List;

public interface ShopMapper {
    //购物车功能
    //查看所有购物信息
    //select * from shop
     List<Shop> selAllShop(User user);

    //用户添加商品到购物车
    //select * from shop where bookid = ? and userid=?
    //update shop set num=num+1 where shopid=?
    //update shop set num=num-1 where shopid=?
    //insert into shop(bookid,userid,num) values(?,?,?)
     boolean insShop(Book book, User user);

    //用户删除购物车商品
    //delete  from shop where shopid = ?
    //update book set booknum = booknum + ? where bookid = ?
     boolean delShop(Shop shop);

    //用户修改购物车商品
    //update shop set num = ? where shopid = ?
     boolean updShop(Shop shop);

    //用户结算购物车
    //select CAST(sum(num*b.bookmoney)  AS decimal(18,2))as money,s.num,s.bookid,s.userid from shop s,book b where userid = ? and b.bookid = s.bookid and s.bookid=?
    //insert into bookshop.order(bookid,booknum,money,userid,ctdate) values(?,?,?,?,?)
    //update user set money = money - ? where id = ?
    //delete from shop where userid = ? and bookid = ?
     boolean sumMoney(User user, String ids[]);
}
