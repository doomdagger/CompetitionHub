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
        //Integer user_id = (Integer) SessionUtil.getValue(request, SessionConst.U_USER, SessionConst.U_USER_LINK);
        //int pageCount = comptService.getPageCount(ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        Pageable pageable = Pageable.inPage(1,ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        List<TCompt> comptList = comptService.getAllComByPage(pageable);
        //pageable.setPageCount(pageCount);
        model.addAttribute("pageable",pageable);
        model.addAttribute("comptList",comptList);
        model.addAttribute(NAV_BAR,CURR_MAN_BAR);
        return "adminComptManList";
    }

    @RequestMapping("/comptList/{currpage}")
    public String listInPage(HttpServletRequest request,@RequestParam int currpage,Model model){
        //Integer user_id = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        //get pageable
        //int pageCount = comptService.getPageCount(ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        //currpage = (currpage<1 || currpage> pageCount)?1:currpage;
        Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        //pageable.setPageCount(pageCount);
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
