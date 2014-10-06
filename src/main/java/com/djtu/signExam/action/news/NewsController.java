package com.djtu.signExam.action.news;

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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private ComptService comptService;

    //用户浏览列表,包含分页
    @RequestMapping(value = {"/list","/"})
    public String broList(Model model){
        //get news list
        Pageable pageable = Pageable.inPage(1,ProjectPageConfig.NEWS_LIST_PAGESIZE);
        List<TNews> newsList = newsService.getAllNewsByPage(pageable);
        //int pageCount = newsService.getPageCount(ProjectPageConfig.NEWS_LIST_PAGESIZE);
        //pageable.setPageCount(pageCount);
        //get compt list
        Pageable comptPanel = Pageable.inPage(1,ProjectPageConfig.COMP_IN_COMP_LIST_NUM);
        List<TCompt> comptList = comptService.getAllComByPage(comptPanel);
        //model data
        model.addAttribute("pageable",pageable);
        model.addAttribute("newsList",newsList);
        model.addAttribute("comptList",comptList);
        return "newsList";
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
        model.addAttribute("newsList",newsList);
        model.addAttribute("pageable",pageable);
        model.addAttribute("comptList",comptList);
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
