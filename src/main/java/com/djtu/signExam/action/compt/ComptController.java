package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.service.compt.ComptService;
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
@RequestMapping("/compt")
public class ComptController {


    @Autowired
    private ComptService comptService;

    @RequestMapping(value = {"/list","/"})
    public String list(){
        return "comptList";
    }

    @RequestMapping("/list/{currpage}")
    public String listInPage(@PathVariable int currpage,Model model){
        //get pageable
        int pageCount = comptService.getPageCount(ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        currpage = (currpage<1 || currpage> pageCount)?1:currpage;
        Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        pageable.setPageCount(pageCount);
        //get data
        List<TCompt> comptList = comptService.getComByPage(pageable);
        model.addAttribute("comptList",comptList);
        model.addAttribute("pageable",pageable);
        return "";
    }


    @RequestMapping("/detail")
    public String detail(@RequestParam String link,Model model){
        TCompt tCompt = new TCompt();
        tCompt = comptService.getComptById(link);
        model.addAttribute("compt",tCompt);
        return "";
    }
}
