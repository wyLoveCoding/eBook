package com.nuc.service.impl;

import com.nuc.mapper.BookMapper;
import com.nuc.pojo.Book;
import com.nuc.pojo.Style;
import com.nuc.service.BookService;
import com.nuc.util.Date;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public Book selectBook(Book book) {

        book = bookMapper.selectBook(book);
        System.out.println("serviceImg------------------"+book.getBookimg());
        String img = book.getBookimg().substring(2,book.getBookimg().length());
        book.setBookimg(img);
        return book;
//        System.out.println(book+"@####%%%%%");
    }

    //查看所有图书
    @Override
    public List<Book> listBook() {
        System.out.println("-----bookService层-----\n");
        List<Book> booklist = bookMapper.listBook();
        int count = booklist.size();


//获取当前的字符串
        for(Book book1:booklist){
//            //遍历Date中的数据
            for(Style style1: Date.MAP){
                if(style1.getBookStyle().equals(book1.getBookStyle())){
                    book1.setBookStyle_value(style1.getBooksName());
                    break;
                }
            }

//            List<Style> list = Date.MAP_TWO.get(book1.getBookStyle());
//            for(Style style1: list){
////                if(style1.getBookStyle().equals(book1.getBookCategory())){
////                    book1.setBookCategory_value(style1.getBooksName());
////                }
////            }
            System.out.println(book1);
            if (book1.getBookimg() != null){
                String path = book1.getBookimg().substring(2);
                book1.setBookimg(path);
            }
//            System.out.println("----path" + path);

//            System.out.println(book1);

        }

        System.out.println("-----bookService层-----\n");
        return booklist;
    }

    /**
     * 根据书名查询图书
     * @param user
     */
    @Override
    public List<Book> searchBook(String bookname) {
        return bookMapper.selByBookName(bookname);
    }

    //修改图书
    @Override
    public boolean updateBook(Book book) {
        System.out.println("-----bookService层updateBook111-----\n"+book+"\n-----bookService层updateBook-----");
        boolean b = bookMapper.updateBook(book);
        System.out.println("b="+b);
        System.out.println("-----bookService层updateBook222-----\n"+book+"\n-----bookService层updateBook-----");
        return b;

    }

    //删除图书
    @Override
    public boolean deleteBook(Book book) {
        boolean result = false;
        System.out.println("进入service");
        result = bookMapper.deleteBook(book);
        System.out.println("---"+result);
        return result;
    }

    //添加图书
    @Override
    public boolean addBook(Book book) {
        boolean result = false;

        result = bookMapper.addBook(book);
        return result;
    }

    //按条件查询图书
    @Override
    public List<Book> listBookKey(Book book) {
        List<Book> bookList = new ArrayList<Book>();

        bookList = bookMapper.listBookKey(book);

        //获取当前的字符串
        for(Book book1:bookList){
            //遍历Date中的数据
            for(Style style1: Date.MAP){
                if(style1.getBookStyle().equals(book1.getBookStyle())){
                    book1.setBookStyle_value(style1.getBooksName());
                    break;
                }
            }
            List<Style> list = Date.MAP_TWO.get(book1.getBookStyle());
            for(Style style1: list){
                if(style1.getBookStyle().equals(book1.getBookCategory())){
                    book1.setBookCategory_value(style1.getBooksName());
                }
            }
            System.out.println(book1);
        }
        return bookList;
    }

    @Override
    public int sumBookKey(Book book) {
        return bookMapper.sumBookKey(book);
    }

    @Override
    public int sumBook() {
        int result = 0;
        result = bookMapper.sumBook();
        return result;
    }
}
