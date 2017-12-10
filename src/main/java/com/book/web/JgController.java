package com.book.web;

import com.book.dao.JgDao;
import com.book.domain.Jg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class JgController {

    private JgDao jgDao;
    @Autowired
    public void setJgDao(JgDao jgDao) {
        this.jgDao = jgDao;
    }

    @RequestMapping("alljgs.html")
    public ModelAndView allJgs(){
        ArrayList<Jg> jgs=jgDao.getAllJgs();
        ModelAndView modelAndView=new ModelAndView("job_jgs");
        modelAndView.addObject("jgs",jgs);
        return modelAndView;
    }

}
