package com.djtu.signExam.action.news;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TComptCalendar;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.calendar.CalendarService;
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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private ComptService comptService;
    @Autowired
    private CalendarService calService;

    //用户浏览列表,包含分页
    @RequestMapping(value = {"/list","/"})
    public String broList(Model model){
        return "redirect:/news/list/1";
    }

    @RequestMapping("/list/{currpage}")
    public String broListInPage(@PathVariable int currpage,Model model){
//        int pageCount = newsService.getPageCount(ProjectPageConfig.NEWS_LIST_PAGESIZE);
//        currpage = (currpage<1 || currpage> pageCount)?1:currpage;
        Pageable pageable = Pageable.inPage(currpage,ProjectPageConfig.NEWS_LIST_PAGESIZE);
        //pageable.setPageCount(pageCount);//设置pageCount
        //get newsList
        List<TNews> newsList = newsService.getAllNewsByPage(pageable);
        //get compt list
        Pageable comptPanel = Pageable.inPage(1,ProjectPageConfig.COMP_IN_COMP_LIST_NUM);
        List<TCompt> comptList = comptService.getAllComByPage(comptPanel);
        //model data
        
        //get calendar list
        List<TComptCalendar> calList = calService.getAllListInPage(Pageable.inPage(1, ProjectPageConfig.CAL_LIST_PAGESIZE));
        model.addAttribute("newsList",newsList);
        model.addAttribute("pageable",pageable);
        model.addAttribute("comptList",comptList);
        model.addAttribute("calList", calList);
        return "newsList";
    }

   @RequestMapping("/detail")
    public String broDetail(@RequestParam String link,Model model){
       TNews tNews = new TNews();
       tNews = newsService.getNewsById(link);
       model.addAttribute("news",tNews);
       return "newsDetail";
   }
}
