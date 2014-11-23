package com.djtu.signExam.action.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TAds;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TComptCalendar;
import com.djtu.signExam.model.TLink;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.model.TShow;
import com.djtu.signExam.service.ads.AdsService;
import com.djtu.signExam.service.calendar.CalendarService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.link.LinkService;
import com.djtu.signExam.service.news.NewsService;
import com.djtu.signExam.service.show.ShowService;

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
    @Autowired
    private AdsService adsService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private ShowService showService;
    @Autowired
    private CalendarService calService;

    /**
     * @param model
     * @return
     */
    @RequestMapping(value={"","/","/index"})
    public String index(Model model){
        //get news list
        List<TNews> newsList = newsService.getAllNewsByPage(Pageable.inPage(1, ProjectPageConfig.NEWS_INDEX_LIST_NUM));//9条
        //get compt list
        List<TCompt> comptList = comptService.getAllIndexListByPage(Pageable.inPage(1,ProjectPageConfig.COMP_INDEX_LIST_NUM));//9条
        //get ads list
        List<TAds> adsList = adsService.getAllAds();
        //get link list
        List<TLink> linkList = linkService.getAllLink();
        //get the show list
        List<TShow> showList = showService.getAllShowInPage(Pageable.inPage(1, ProjectPageConfig.SHOW_INDEX_LIST_NUM));
        //get calendar list
        List<TComptCalendar> calList = calService.getAllListInPage(Pageable.inPage(1, ProjectPageConfig.CAL_LIST_PAGESIZE));
        
        //get show list
        model.addAttribute("newsList",newsList);
        model.addAttribute("comptList",comptList);
        model.addAttribute("adsList", adsList);
        model.addAttribute("linkList", linkList);
        model.addAttribute("showList", showList);
        model.addAttribute("calList", calList);
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

    @RequestMapping("/showMessage")
    public String showMessage(String message,Model model){
        if(message == null || message == ""){
            message = "您的访问不正确";
        }
        return "";
    }
    
    @RequestMapping("/showUploadError")
    public String showUploadError(){
    	return "uploadErrorMessage";
    }

}
