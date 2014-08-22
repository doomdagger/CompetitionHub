package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.util.JsonUtil;
import com.djtu.signExam.util.StringConst;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * Created by JOECHOW on 2014/8/21.
 */

@Controller
@RequestMapping("/man/compt")
public class ComptManController {

    @Autowired
    private ComptService comptService;

    //const for page nav
    private final String NAV_BAR = "navbar";
    private final String CURR_PUB_BAR = "comptPub";
    private final String CURR_MAN_BAR = "comptMan";

    @RequestMapping(value={"/list","/"})
    public String list(Model model){

        int pageCount = comptService.getPageCount(ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        Pageable pageable = Pageable.inPage(1,ProjectPageConfig.MAN_NEWS_LIST_PAGESIZE);
        List<TCompt> comptList = comptService.getComByPage(pageable);
        pageable.setPageCount(pageCount);
        model.addAttribute("pageable",pageable);
        model.addAttribute("compt",comptList);
        model.addAttribute(NAV_BAR,CURR_MAN_BAR);
        return "comptManList";
    }

    @RequestMapping("/list/{currpage}")
    public String listInPage(@RequestParam int currpage,Model model){
        //get pageable
        int pageCount = comptService.getPageCount(ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        currpage = (currpage<1 || currpage> pageCount)?1:currpage;
        Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        pageable.setPageCount(pageCount);
        //get data
        List<TCompt> comptList = comptService.getComByPage(pageable);
        model.addAttribute("comptList",comptList);
        model.addAttribute("pageable",pageable);
        model.addAttribute(NAV_BAR,CURR_MAN_BAR);
        return "comptManList";
    }

    @RequestMapping("/publishGet")
    public String publishGet(String isSubmit,Model model){
        model.addAttribute(NAV_BAR,CURR_PUB_BAR);
        model.addAttribute("isSubmit",isSubmit);
        return "comptManPub";
    }

/*    title:编程
    sponsor:大连
    level:1
    address:大连
    comptDate:2015
    deadline_s:
    deadline_e:
    content:你好
    comptIntro:你好
    sp_type:1
    sp_maxMember:1
    sp_isWroks:1
    sp_worksIntro:
    isSupport:1*/

    @RequestMapping(value="/publishPost",method = RequestMethod.POST)
    public String publishPost(@RequestParam String title, String sponsor, String level,String address,String comptDate,
                              Date deadline_s,Date deadline_e,String content,String comptIntro,
                              String sp_type,@RequestParam int maxMember,String sp_isWorks,String sp_worksIntro,
                              String isSupport,String supportIntro,RedirectAttributes redirectAttributes){
        //form page post
        TCompt compt = new TCompt();
        compt.setTitle(title);
        compt.setSponsor(sponsor);
        compt.setLevel(new Integer(level));
        compt.setComptDate(comptDate);
        compt.setAddress(address);
        compt.setContent(content);
        compt.setComptIntro(comptIntro);
        compt.setDeadlineS(deadline_s);
        compt.setDeadlineE(deadline_e);
        compt.setSpType(sp_type == "1" ? true : false);
        compt.setSpMaxMember(maxMember);
        compt.setSpIsWorks(sp_isWorks == "1" ? true : false);
        compt.setSpWorksIntro(sp_worksIntro);
        compt.setIsSupport(isSupport == "1" ? true : false);
        compt.setSupportIntro(supportIntro);
        compt.setIsDelete(false);
        compt.setStatus(1);
        compt.setCreatetime(new Date());
        comptService.addCompt(compt);
        redirectAttributes.addAttribute("isSubmit", "success");
        return "redirect:/man/compt/publishGet";
    }

    @RequestMapping("/updateGet")
    public String updateGet(@RequestParam String link){
        return  "comptManPub";
    }

    @RequestMapping("/updateStatus/{link}/{code}")
    public String updateStatus(@PathVariable String link,@PathVariable int code){
        if(link==""||link==null || code == 0 ){
            return "redirect:/man/compt/list/1";//重定向
        }
        comptService.updateStatus(link,code);//do update status
        return "redirect:/man/compt/list/1";//重定向
    }

    @RequestMapping("/delete/{link}")
    public String delete(@RequestParam String link){
        //ajax post do delete
        return StringConst.AJAX_SUCCESS;
    }
}
