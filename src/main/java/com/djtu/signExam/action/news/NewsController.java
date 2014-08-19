package com.djtu.signExam.action.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 14-7-24.
 */

@Controller
@RequestMapping("/news")
public class NewsController {

    //用户浏览列表,包含分页
    @RequestMapping("/broList")
    public String broList(){
        return "newsList";
    }

   @RequestMapping("/broDetail")
    public String broDetail(){
       return "newsDetail";
   }


}
