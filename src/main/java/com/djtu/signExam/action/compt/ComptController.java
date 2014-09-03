package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TComptAttchment;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.compt.ComptAttchmentService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.news.NewsService;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.SessionConst;
import com.djtu.signExam.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 14-7-24.
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

    @RequestMapping(value = {"/list","/"})
    public String list(Model model){
        //get comp list
        int pageCount = comptService.getPageCount(ProjectPageConfig.COMP_LIST_PAGESIZE);
        Pageable pageable = Pageable.inPage(1,ProjectPageConfig.COMP_LIST_PAGESIZE);
        List<TCompt> comptList = comptService.getComByPage(pageable);
        pageable.setPageCount(pageCount);
        //get newsList
        Pageable newsPanel = Pageable.inPage(1,ProjectPageConfig.NEWS_IN_COMP_LIST_NUM);
        List<TNews> newsList = newsService.getNewsByPage(newsPanel);
        //model data
        model.addAttribute("pageable",pageable);
        model.addAttribute("comptList",comptList);
        model.addAttribute("newsList",newsList);
        return "comptList";
    }

    @RequestMapping("/list/{currpage}")
    public String listInPage(@PathVariable int currpage,Model model){
        //get pageable
        int pageCount = comptService.getPageCount(ProjectPageConfig.COMP_LIST_PAGESIZE);
        currpage = (currpage<1 || currpage> pageCount)?1:currpage;
        Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.COMP_LIST_PAGESIZE);
        pageable.setPageCount(pageCount);
        //get newsList
        Pageable newsPanel = Pageable.inPage(1,ProjectPageConfig.NEWS_IN_COMP_LIST_NUM);
        List<TNews> newsList = newsService.getNewsByPage(newsPanel);
        //get data
        List<TCompt> comptList = comptService.getComByPage(pageable);
        model.addAttribute("comptList",comptList);
        model.addAttribute("pageable",pageable);
        model.addAttribute("newsList",newsList);
        return "comptList";
    }


    @RequestMapping("/detail")
    public String detail(@RequestParam String link,Model model){
        TCompt tCompt = new TCompt();
        tCompt = comptService.getComptById(link);
        //get attachment
        List<TComptAttchment> attchmentList = attchmentService.getAllItemsBySkId(link);
        model.addAttribute("compt",tCompt);
        model.addAttribute("attachList",attchmentList);
        return "comptDetail";
    }

    @RequestMapping("/signIn")
    public String signIn(@RequestParam String link,HttpServletRequest request,HttpServletResponse response,Model model){
        if(link ==null ||link == ""){
            return "redirect:/status/404";
        }
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        TUserStudent student = userService.getStudentInfo(userId!=null?userId.toString():"");
        TCompt compt = comptService.getComptById(link);
        model.addAttribute("student",student);
        model.addAttribute("compt",compt);
        model.addAttribute("link",link);
        return "comptSignIn";
    }
}
