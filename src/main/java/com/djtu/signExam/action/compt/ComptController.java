package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.*;
import com.djtu.signExam.service.calendar.CalendarService;
import com.djtu.signExam.service.compt.ComptAttchmentService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.compt.ComptSigninService;
import com.djtu.signExam.service.news.NewsService;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.SessionConst;
import com.djtu.signExam.util.SessionUtil;
import com.djtu.signExam.util.StringConst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Created by root on 14-7-24.
 * 包含了现实前台比赛列表和比赛详情
 */
@Controller
@RequestMapping("/compt")
public class ComptController {


    @Autowired
    private ComptService comptService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private ComptAttchmentService attchmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ComptSigninService signinService;
    @Autowired
    private CalendarService calService;

    @RequestMapping(value = {"/list","/"})
    public String list(HttpServletRequest request,Model model){
        return "redirect:/compt/list/1";
    }

    @RequestMapping("/list/{currpage}")
    public String listInPage(HttpServletRequest request,@PathVariable int currpage,Model model){
        //get pageable
        //int pageCount = comptService.getPageCount(ProjectPageConfig.COMP_LIST_PAGESIZE);
        //currpage = (currpage<1 || currpage> pageCount)?1:currpage;
        //String user_id = (String) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.COMP_LIST_PAGESIZE);
        //get data
        List<TCompt> comptList = comptService.getAllComByPage(pageable);
        //pageable.setPageCount(pageCount);
        //get newsList
        Pageable newsPanel = Pageable.inPage(1,ProjectPageConfig.NEWS_IN_COMP_LIST_NUM);
        List<TNews> newsList = newsService.getAllNewsByPage(newsPanel);
        
        //get calendar list
        List<TComptCalendar> calList = calService.getAllListInPage(Pageable.inPage(1, ProjectPageConfig.CAL_LIST_PAGESIZE));

        model.addAttribute("comptList",comptList);
        model.addAttribute("pageable",pageable);
        model.addAttribute("newsList",newsList);
        model.addAttribute("calList", calList);
        return "comptList";
    }


    @RequestMapping("/detail")
    public String detail(@RequestParam String link,Model model){
        TCompt tCompt = new TCompt();
        tCompt = comptService.getComptById(link);
        //get attachment
        List<TComptAttachment> attchmentList = attchmentService.getAllItemsBySkId(link);
        model.addAttribute("compt",tCompt);
        model.addAttribute("attachList",attchmentList);
        return "comptDetail";
    }


}
