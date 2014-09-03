package com.djtu.signExam.action.index;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by root on 14-7-23.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ComptService comptService;
    @Autowired
    private NewsService newsService;

    @RequestMapping(value={"","/index"})
    public String index(Model model){
        //get news list
        List<TNews> newsList = newsService.getNewsByPage(Pageable.inPage(1, ProjectPageConfig.NEWS_INDEX_LIST_NUM));//9条
        //get comp list
        List<TCompt> comptList = comptService.getComByPage(Pageable.inPage(1,ProjectPageConfig.COMP_INDEX_LIST_NUM));//9条
        //get show list
        model.addAttribute("newsList",newsList);
        model.addAttribute("comptList",comptList);
        return "index";
    }

    @RequestMapping("/aboutUs")
    public String aboutUs(){
        return "aboutUs";
    }



    //Error handler
    @RequestMapping("/noPermission")
    public String noPermission(){
        return "noPermission";
    }

}
