package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("compt",tCompt);
        return "comptDetail";
    }
}
