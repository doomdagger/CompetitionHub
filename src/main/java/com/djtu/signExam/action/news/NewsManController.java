package com.djtu.signExam.action.news;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.news.NewsService;
import com.djtu.signExam.util.DateUtil;
import com.djtu.signExam.util.StringConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 14-7-24.
 */

@Controller
@RequestMapping("/man/news")
public class NewsManController {

    @Autowired
    public NewsService newsService;

    @RequestMapping("/manList")
    public String manList(Model model){
        List<TNews> newsList = newsService.getNewsByPage(Pageable.inPage(1, ProjectPageConfig.B_newsListPageSize));
        model.addAttribute("newsList",newsList);
        return "newsMan";
    }
    //管理列表，包含分页
    @RequestMapping("/manList/{currpage}")
    public String manListByPage(@PathVariable int currpage, Model model){
        //currpage should be validated
        List<TNews> newsList = newsService.getNewsByPage(Pageable.inPage(currpage, ProjectPageConfig.B_newsListPageSize));
        model.addAttribute("newsList",newsList);
        return "newsMan";
    }

    //发布新闻
    @RequestMapping("/manPublishGet")
    public String manPublishGet(){
        return "newsManPub";
    }

    @RequestMapping("/manPublishPost")
    public @ResponseBody String manPublishPost(@RequestParam String title,@RequestParam String content,@RequestParam String isTop){
        TNews tNews = new TNews();
        tNews.setContent(content);
        tNews.setIsDelete(false);
        tNews.setTitle(title);
        tNews.setCreatetime(new Date());
        tNews.setSkTUserAdmin(1);
        tNews.setIsTop(isTop.equals("1")?true:false);
        if(newsService.addNews(tNews)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    @RequestMapping("/manDetail")
    public String manDetail(){
        return "";
    }

    @RequestMapping("/manUpdate")
    public String manUpdate(){
        return "";
    }

    @RequestMapping("/manDelete")
    public String manDelete(){
        return "";
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
