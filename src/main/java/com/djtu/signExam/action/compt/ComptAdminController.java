package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.service.compt.ComptAttchmentService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.util.SessionConst;
import com.djtu.signExam.util.SessionUtil;
import com.djtu.signExam.util.StringConst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Created by JOECHOW on 2014/9/10.
 */
@Controller
@RequestMapping("/man/admin")
public class ComptAdminController {

    @Autowired
    private ComptService comptService;
    @Autowired
    private ComptAttchmentService comptAttchmentService;

    //const for page nav
    private final String NAV_BAR = "navbar";
    private final String CURR_PUB_BAR = "comptPub";
    private final String CURR_MAN_BAR = "comptMan";

    @RequestMapping(value={"/comptList","/"})
    public String list(HttpServletRequest request,Model model){
        return "redirect:/man/admin/comptList/1";
    }
    
    
    @RequestMapping("/comptList/{currpage}")
    public String listInPage(HttpServletRequest request,@PathVariable int currpage,Model model){
        Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        //get data
        List<TCompt> comptList = comptService.getAllComByPage(pageable);
        model.addAttribute("comptList",comptList);
        model.addAttribute("pageable",pageable);
        model.addAttribute(NAV_BAR,CURR_MAN_BAR);
        return "adminComptManList";
    }
    
    //改变状态
    @RequestMapping("/changeStatus")
    public @ResponseBody String changeStatus(@RequestParam String link,@RequestParam int status){
        if(comptService.updateStatus(link,status)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }
}
