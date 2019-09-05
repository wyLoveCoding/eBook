package com.nuc.service;

import com.nuc.pojo.Book;
import com.nuc.pojo.User;

import java.util.List;

public interface BookService {

    /**
     * 图书管理
     */

    //查看某一图书
    public Book selectBook(Book book);

    //查看所有图书
    public List<Book> listBook();

    //修改图书
    public boolean updateBook(Book book);

    //删除图书
    public boolean deleteBook(Book book);

    //添加图书
    public boolean addBook(Book book);

    //按条件查询图书
    public List<Book> listBookKey(Book book);

    //根据书名查询图书
    public List<Book> searchBook(String bookname);

    //按条件查询所有书籍总数
    public int sumBookKey(Book book);

    //查询所有书籍总数
    public int sumBook();
}
