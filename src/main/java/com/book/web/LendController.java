package com.book.web;

import com.book.domain.Book;
import com.book.domain.ReaderCard;
import com.book.service.BookService;
import com.book.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LendController {

    private LendService lendService;
    @Autowired
    public void setLendService(LendService lendService) {
        this.lendService = lendService;
    }
    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/lendbook.html")
    public ModelAndView bookLend(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
       ModelAndView modelAndView=new ModelAndView("admin_book_lend");
       modelAndView.addObject("book",book);
       return modelAndView;
    }
    @RequestMapping("/lendbook1.html")
    public ModelAndView bookLend1(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("job_book_lend");
        modelAndView.addObject("book",book);
        return modelAndView;
    }

    @RequestMapping("/lendbookdo.html")
    public String bookLendDo(HttpServletRequest request,RedirectAttributes redirectAttributes,int readerId){
        long bookId=Integer.parseInt(request.getParameter("id"));
        boolean lendsucc=lendService.bookLend(bookId,readerId);
        if (lendsucc){
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
            return "redirect:/allbooks.html";
        }else {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
            return "redirect:/allbooks.html";
        }


    }
    @RequestMapping("/lendbookdo1.html")
    public String bookLendDo1(HttpServletRequest request,RedirectAttributes redirectAttributes,int readerId){
        long bookId=Integer.parseInt(request.getParameter("id"));
        boolean lendsucc=lendService.bookLend(bookId,readerId);
        if (lendsucc){
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
            return "redirect:/allbooks1.html";
        }else {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
            return "redirect:/allbooks1.html";
        }


    }

    @RequestMapping("/returnbook1.html")
    public String bookReturn(HttpServletRequest request,RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        boolean retSucc=lendService.bookReturn(bookId);
        if (retSucc){
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
            return "redirect:/allbooks1.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "图书归还失败！");
            return "redirect:/allbooks1.html";
        }
    }


    @RequestMapping("/lendlist.html")
    public ModelAndView lendList(){

        ModelAndView modelAndView=new ModelAndView("admin_lend_list");
        modelAndView.addObject("list",lendService.lendList());
        return modelAndView;
    }


    @RequestMapping("/ lendlistcount.html")
    public ModelAndView lendListCount(){

        ModelAndView modelAndView=new ModelAndView("admin_lend_list_count");
        modelAndView.addObject("list",lendService.lendList());
        int count = lendService.lendListCount();
        modelAndView.addObject("count",count);
        return modelAndView;
    }
    @RequestMapping("/mylend.html")
    public ModelAndView myLend(HttpServletRequest request){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView=new ModelAndView("reader_lend_list");
        modelAndView.addObject("list",lendService.myLendList(readerCard.getReaderId()));
        return modelAndView;
    }




}
