package com.djtu.signExam.action.news;

import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by root on 14-7-24.
 */

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    //用户浏览列表,包含分页
    @RequestMapping("/list")
    public String broList(){
        return "newsList";
    }

    @RequestMapping("/list/{currpage}")
    public String broListInPage(@PathVariable int currpage){
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
