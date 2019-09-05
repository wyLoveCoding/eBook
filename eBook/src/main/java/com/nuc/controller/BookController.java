package com.nuc.controller;

import com.nuc.pojo.Book;
import com.nuc.pojo.User;
import com.nuc.service.BookService;
import com.nuc.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Book")
public class BookController {

    private List<Book> bookList = new ArrayList<Book>();

    @Resource
    private BookService bookServiceImpl;

    /**
     * 管理登录成功界面
     *
     * @param book
     * @param model
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(Book book, Model model) {
        book.setBookStyle("1");
        bookList = bookServiceImpl.listBook();
        model.addAttribute("bookList", bookList);
        return "admin/admin.jsp";
    }

    /**
     * 管理员根据书名查询图书
     */
    @RequestMapping("/search")
    public String searchByName(String bookname, HttpServletRequest request){
        System.out.println("bookname-------------" + bookname);
        List<Book> bookList = bookServiceImpl.searchBook(bookname);
        request.setAttribute("bookList", bookList);
        System.out.println("--------------bookList-------------------");
        bookList.forEach(System.out::println);
        System.out.println("--------------bookList-------------------");
        return "book/book-list";
    }


    /**
     * 管理员查看所有图书
     *
     * @param book
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Book book, Model model) {
        System.out.println("\n\n\n\n执行booklist\n\n\n\n");
        book.setBookStyle("all");
        bookList = bookServiceImpl.listBook();
        model.addAttribute("bookList", bookList);
        model.addAttribute("size", bookList.size());
        return "book/book-list";
    }

    /**
     * 更新图书信息
     *
     * @param book
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String update(Book book, Model model) {
        System.out.println("-----bookcontroller层book-----\n" + book + "\\n-----bookcontroller层-----");
        System.out.println("-----bookcontroller层book.getBookid()-----\n" + book.getBookid() + "\\n-----bookcontroller层-----");
        if (bookServiceImpl.updateBook(book)) {                                             //执行修改并判断是否修改成功
            model.addAttribute("message", "修改成功！");
        } else {
            model.addAttribute("message", "修改失败！");
        }

        book.setBookStyle("all");
        bookList = bookServiceImpl.listBook();
        model.addAttribute("bookList", bookList);
        return "/Book?page=1";
    }

    /**
     * 添加图书
     *
     * @param book
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Book book, Model model) {
        System.out.println("进入add");
        System.out.println(book);
        if (bookServiceImpl.addBook(book)) {                                                //执行添加并判断是否添加成功
            model.addAttribute("message", "添加成功！");
            System.out.println("添加成功");
        } else {
            model.addAttribute("message", "添加失败！");
            System.out.println("添加失败");
        }

        return "/Book/list";
    }

    /**
     * 删除图书
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public Object del(@RequestParam int id, HttpServletResponse response) {

        System.out.println(id);
        Book book = new Book();
        book.setBookid(id);
        System.out.println("111" + book.getBookid());
        int result = 0;
        if (bookServiceImpl.deleteBook(book)) {                                              //执行删除并判断是否删除成功
            result = 1;
            System.out.println("删除成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        } else {
            System.out.println("删除失败！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        }
        return result;
    }

    /**
     * 按关键字进行查询
     */
    /*@RequestMapping("/keyList")
    public String keyList(Book book){

    }*/


    /**
     * 图片上传的时候显示图片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/img")
    public void img(HttpServletRequest request, HttpServletResponse response) {
        String s = FileUpload.getFileUpload(request.getSession().getServletContext().getRealPath("upload/"), request);
        s = s.replaceAll("\\\\", "/");
        int index = s.indexOf('_');
        s = s.substring(index + 13, s.length());
        s = "." + s;
        try {
            PrintWriter out = response.getWriter();
            out.print(s);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到添加图书页面
     *
     * @return
     */
    @RequestMapping("/addbook")
    public String addbook() {
        return "book/book-add";
    }

    /**
     * 查出所要修改的图书 并且跳转到修改图书信息页面
     *
     * @param book
     * @param model
     * @return
     */
    @RequestMapping("/updatebook")
    public String updatebook(Book book, Model model) {

        Book book1 = bookServiceImpl.selectBook(book);
        model.addAttribute("book", book1);
        System.out.println(book1.getBookid());
        return "book/book-update";
    }

    /*
     *批量删除
     */
    @RequestMapping("/deleteMore")
    public String deleteMore(String ids, Map<String, Object> map) {
        System.out.println("ids----------" + ids);
        String a[] = ids.split(",");
        Book book = new Book();
        for (int i = 0; i < a.length; i++) {
            String id = a[i];
            book.setBookid(Integer.parseInt(id));
            bookServiceImpl.deleteBook(book);
            map.put("msg", 1);
        }
        return "book/book-list";
    }
}
