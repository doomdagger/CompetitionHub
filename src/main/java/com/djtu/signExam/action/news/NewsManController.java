package com.djtu.signExam.action.news;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.news.NewsService;
import com.djtu.signExam.util.SessionConst;
import com.djtu.signExam.util.SessionUtil;
import com.djtu.signExam.util.StringConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
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
    public String manList(HttpServletRequest request,Model model){
        Boolean istp = (Boolean)SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_ISTP);
        Pageable pageable = Pageable.inPage(1,ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        List<TNews> newsList;
        if(istp){
            newsList = newsService.getAllNewsByPage(pageable);
        }else{
            Integer userId = (Integer) SessionUtil.getValue(request, SessionConst.U_USER, SessionConst.U_USER_LINK);
            newsList = newsService.getNewsByPage(userId.toString(),pageable);
        }

        //int pageCount = newsService.getPageCount(ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        //Pageable pageable = Pageable.inPage(1,ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        //pageable.setPageCount(pageCount);
        model.addAttribute("pageable",pageable);
        model.addAttribute("newsList",newsList);
        model.addAttribute(NAV_BAR,CURR_BAR);
        return "newsManList";
    }

    //管理列表，包含分页
    @RequestMapping("/manList/{currpage}")
    public String manListByPage(HttpServletRequest request,@PathVariable int currpage, Model model){
        Boolean istp = (Boolean)SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_ISTP);
        Pageable pageable = Pageable.inPage(currpage,ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        List<TNews> newsList;
        if(istp){
            newsList = newsService.getAllNewsByPage(pageable);
        }else{
            Integer userId = (Integer) SessionUtil.getValue(request, SessionConst.U_USER, SessionConst.U_USER_LINK);
            newsList = newsService.getNewsByPage(userId.toString(),pageable);
        }
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
    public @ResponseBody String manPublishPost(HttpServletRequest request,@RequestParam String title,@RequestParam String content,@RequestParam String isTop){
        Integer userId = (Integer)SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        String userName = (String)SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_NAME);
        TNews tNews = new TNews();
        content = content.replaceAll("\\\\","\\\\\\\\");
        tNews.setContent(content);
        tNews.setIsDelete(false);
        tNews.setTitle(title);
        tNews.setCreatetime(new Date());
        tNews.setSkTUserAdmin(userId);
        tNews.setAdminName(userName);
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
    public @ResponseBody String manTotop(@PathVariable int value,@RequestParam String newsId){
        if(!"".equals(newsId)){
            TNews tNews = newsService.getNewsById(newsId);
            if(1==value){
                tNews.setCreatetime(new Date());
                tNews.setIsTop(true);
            }else{
                tNews.setIsTop(false);
            }
            newsService.updateNews(tNews);
        }
        return StringConst.AJAX_SUCCESS;
    }
}
