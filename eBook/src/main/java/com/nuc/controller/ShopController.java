package com.nuc.controller;

import com.nuc.pojo.Book;
import com.nuc.pojo.Shop;
import com.nuc.pojo.User;
import com.nuc.service.BookService;
import com.nuc.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("Shop")
public class ShopController {

    @Resource
    private ShopService shopServiceImpl;

    @Resource
    private BookService bookServiceImpl;

    /**
     * @description                 查看用户购物记录
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<Shop> list = shopServiceImpl.listShop(user);
        int numid = 0;
        for (Shop shop1: list) {
            Book book1 = new Book();
            int bookId = shop1.getBookId();
            book1.setBookid(bookId);
            bookServiceImpl.selectBook(book1);
            book1.setBookAuthor(book1.getBookMoney()*shop1.getNum()+"");
            System.out.println(book1);
            shop1.setBook(book1);
            shop1.setUser(user);
            shop1.setNumid(numid);
            numid++;
        }
        request.setAttribute("list",list);
        return "shop/shopCart";

    }

    /**
     * @description                 添加书籍到购物车
     * @param
     */
    @RequestMapping("/add")
    public String add(Book book,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        book.setBookNum(1);
        shopServiceImpl.addShop(book,user);
        return "Book/keyList&style1=style&style=2&page=1";
    }

    /**
     * @description                 删除购物车的一栏
     */
    @RequestMapping("/shop")
    public void delete(Shop shop,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        shopServiceImpl.deleteShop(shop);
    }

    /**
     * @description                 修改购物车信息
     */
    @RequestMapping("/update")
    public void update(Shop shop,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        shopServiceImpl.updateShop(shop);
    }

    /**
     * @description                 结算
     */
    @RequestMapping("/sum")
    public void sum(HttpServletRequest request){
        String ids[] = request.getParameter("ids").split(",");
        User user = (User) request.getSession().getAttribute("user");
        shopServiceImpl.sumMoney(user,ids);
    }

    /**
     * @description                 加一
     */
    @RequestMapping("/add1")
    public void add1(Book book,HttpServletRequest request){
        book.setBookNum(1);
        User user = (User) request.getSession().getAttribute("user");
        shopServiceImpl.addShop(book,user);
    }

    /**
     * @description                 减一
     */
    @RequestMapping("/delete1")
    public void delete1(Book book,HttpServletRequest request){
        book.setBookNum(-1);
        User user = (User) request.getSession().getAttribute("user");
        shopServiceImpl.addShop(book,user);
    }
}
