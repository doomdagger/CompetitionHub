package com.djtu.signExam.action.news;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.news.NewsService;
import com.djtu.signExam.util.StringConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 14-7-24.
 */

@Controller
@RequestMapping("/man/news")
public class NewsManController {

    private final String NAV_BAR = "navbar";
    private final String CURR_BAR = "news";
    @Autowired
    public NewsService newsService;

    @RequestMapping(value = {"/manList","/"})
    public String manList(Model model){
        List<TNews> newsList = newsService.getNewsByPage(Pageable.inPage(1, ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE));
        int pageCount = newsService.getPageCount(ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        Pageable pageable = Pageable.inPage(1,ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        pageable.setPageCount(pageCount);
        model.addAttribute("pageable",pageable);
        model.addAttribute("newsList",newsList);
        model.addAttribute(NAV_BAR,CURR_BAR);
        return "newsManList";
    }

    //管理列表，包含分页
    @RequestMapping("/manList/{currpage}")
    public String manListByPage(@PathVariable int currpage, Model model){
        //currpage should be validated
        //如果输入当前页小于1或大于总页数 都显示第一页
        int pageCount = newsService.getPageCount(ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        System.out.println("curr:"+currpage+" count:"+pageCount);
        currpage = (currpage<1 || currpage> pageCount)?1:currpage;
        System.out.println("curr:"+currpage+" count:"+pageCount);
        Pageable pageable = Pageable.inPage(currpage,ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        pageable.setPageCount(pageCount);//设置pageCount
        //get newsList
        List<TNews> newsList = newsService.getNewsByPage(pageable);
        model.addAttribute("newsList",newsList);
        model.addAttribute("pageable",pageable);
        model.addAttribute(NAV_BAR,CURR_BAR);
        return "newsManList";
    }

    //发布新闻
    @RequestMapping("/manPublishGet")
    public String manPublishGet(Model model){
        model.addAttribute(NAV_BAR,CURR_BAR);
        return "newsManPub";
    }

    @RequestMapping("/manPublishPost")
    public @ResponseBody String manPublishPost(@RequestParam String title,@RequestParam String content,@RequestParam String isTop){
        TNews tNews = new TNews();
        content = content.replaceAll("\\\\","\\\\\\\\");
        tNews.setContent(content);
        tNews.setIsDelete(false);
        tNews.setTitle(title);
        tNews.setCreatetime(new Date());
        tNews.setSkTUserAdmin(1);
        tNews.setIsTop(isTop.equals("1") ? true : false);

        System.out.println("content:"+content);
        if(newsService.addNews(tNews)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    @RequestMapping("/manDetail")
    public String manDetail(){
        return "";
    }

    //编辑新闻Get
    //link 替代ID
    @RequestMapping("/manUpdateGet")
    public String manUpdateGet(@RequestParam String link,Model model){
        TNews tNews = newsService.getNewsById(link);
        model.addAttribute("news",tNews);
        model.addAttribute(NAV_BAR,CURR_BAR);
        return "newsManPub";
    }

    @RequestMapping("/manDelete")
    public @ResponseBody String manDelete(@RequestParam String newsId){
        //ajax delete
        if(newsService.deleteNewsById(newsId)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //Ajax 置顶/取消置顶 接收参数type 0为取消置顶 1为置顶
    @RequestMapping("/manTotop/{value}")
    public @ResponseBody String manTotop(@PathVariable int value,@RequestParam int newsId){
        if(!"".equals(newsId)){
            TNews tNews = new TNews();
            tNews.setID(newsId);
            if(1==value){
                tNews.setCreatetime(new Date());
                tNews.setIsTop(true);
            }else{
                tNews.setIsTop(false);
            }
            System.out.println("get in");
            newsService.updateNews(tNews);
        }
        return StringConst.AJAX_SUCCESS;
    }
}
