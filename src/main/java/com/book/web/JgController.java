package com.book.web;

import com.book.dao.JgDao;
import com.book.domain.Jg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class JgController {

    private JgDao jgDao;

    @Autowired
    public void setJgDao(JgDao jgDao) {
        this.jgDao = jgDao;
    }

    @RequestMapping("alljgs.html")
    public ModelAndView allJgs() {
        ArrayList<Jg> jgs = jgDao.getAllJgs();
        ModelAndView modelAndView = new ModelAndView("job_jgs");
        modelAndView.addObject("jgs", jgs);
        return modelAndView;
    }

    @RequestMapping("/jg1.html")
    public String jg1(HttpServletRequest request, RedirectAttributes redirectAttributes, int jgId) {
       /* int jgId = Integer.parseInt(request.getParameter("jgId"));*/
        jgDao.jgState(1, jgId);
        redirectAttributes.addFlashAttribute("succ", "荐购同意成功！");
        return "redirect:/alljgs.html";
    }

    @RequestMapping("/jg2.html")
    public String jg2(HttpServletRequest request, RedirectAttributes redirectAttributes, int jgId) {
        /*int jgId = Integer.parseInt(request.getParameter("jgId"));*/
        jgDao.jgState(2, jgId);
        redirectAttributes.addFlashAttribute("succ", "荐购拒绝成功！");
        return "redirect:/alljgs.html";
    }

    @RequestMapping("/addjg1.html")
    public String addJg1(HttpServletRequest request) {
        String jgName = request.getParameter("jgName");
        String jgReason = request.getParameter("jgReason");
        Jg jg = new Jg();
        jg.setJgName(jgName);
        jg.setJgReason(jgReason);
        jgDao.jgAdd(jg);
        return "redirect:/alljgs1.html";
    }
    @RequestMapping("/addjg.html")
    public ModelAndView addJg(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView("reader_jg_add");
        return modelAndView;
    }

    @RequestMapping("alljgs1.html")
    public ModelAndView allJgs1() {
        ArrayList<Jg> jgs = jgDao.getAllJgs();
        ModelAndView modelAndView = new ModelAndView("reader_jgs");
        modelAndView.addObject("jgs", jgs);
        return modelAndView;
    }

}
