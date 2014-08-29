package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TComptAttchment;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.compt.ComptAttchmentService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.util.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    private ComptAttchmentService comptAttchmentService;

    //const for page nav
    private final String NAV_BAR = "navbar";
    private final String CURR_PUB_BAR = "comptPub";
    private final String CURR_MAN_BAR = "comptMan";

    @RequestMapping(value={"/list","/"})
    public String list(Model model){

        int pageCount = comptService.getPageCount(ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        Pageable pageable = Pageable.inPage(1,ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        List<TCompt> comptList = comptService.getComByPage(pageable);
        pageable.setPageCount(pageCount);
        model.addAttribute("pageable",pageable);
        model.addAttribute("comptList",comptList);
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
    public String publishGet(String link,String isSubmit,Model model){
        model.addAttribute(NAV_BAR,CURR_PUB_BAR);
        model.addAttribute("isSubmit",isSubmit);
        model.addAttribute("link",link);
        model.addAttribute("status",0);
        return "comptManPub";
    }

    //新增赛事
    @RequestMapping(value="/publishPost",method = RequestMethod.POST)
    public String publishPost(@RequestParam String title,
                              @RequestParam String sponsor,
                              @RequestParam String level,
                              @RequestParam String address,
                              @RequestParam String comptDate,
                              @RequestParam String deadline_s,
                              @RequestParam String deadline_e,
                              @RequestParam String content,
                              @RequestParam String comptIntro,
                              @RequestParam int sp_type,
                              @RequestParam int sp_maxMember,
                              @RequestParam int sp_isWorks,
                              @RequestParam String sp_worksIntro,
                              @RequestParam int isSupport,
                              @RequestParam String supportIntro,
                              @RequestParam int currentStatus,
                              @RequestParam int link,//edit
                              RedirectAttributes redirectAttributes){
        //form page post
        TCompt compt = new TCompt();
        compt.setTitle(title);
        compt.setSponsor(sponsor);
        compt.setLevel(new Integer(level));
        compt.setComptDate(comptDate);
        compt.setAddress(address);
        compt.setContent(content.replaceAll("\\\\","\\\\\\\\"));//处理路径信息
        compt.setComptIntro(comptIntro.replaceAll("\\\\","\\\\\\\\"));//处理路径信息
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date deadline_start = simpleDateFormat.parse(deadline_s+" 00:00:00");
            Date deadline_end = simpleDateFormat.parse(deadline_e+" 00:00:00");
            compt.setDeadlineS(deadline_start);
            compt.setDeadlineE(deadline_end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        compt.setSpType(sp_type == 1 ? true : false);
        compt.setSpNum(sp_maxMember);
        compt.setIsNeedFile(sp_isWorks == 1 ? true : false);
        compt.setNeedFileIntro(sp_worksIntro);
        compt.setIsSupport(isSupport == 1 ? true : false);
        compt.setSupportIntro(supportIntro);
        compt.setIsDelete(false);
        compt.setStatus(1);
        compt.setCreatetime(new Date());
        compt.setIsVaild(currentStatus < 2 ? false : true);
        compt.setIsTop(false);
        compt.setStatus(1);//无论现在是何种状态，status状态字都将回到1（重新提交状态）
        Integer comptId = link;
        if(currentStatus==0){
            comptId = comptService.addCompt(compt);//new
        }else{
            compt.setID(link);
            comptService.updateCompt(compt);//edit
        }


        redirectAttributes.addAttribute("isSubmit", "success");
        redirectAttributes.addAttribute("link", comptId);
        return "redirect:/man/compt/publishGet";
    }

    //上传附件页面
    @RequestMapping("/uploadGet")
    public String uploadGet(@RequestParam String link,Model model){
        List<TComptAttchment> attachList = comptAttchmentService.getAllItemsBySkId(link);
        model.addAttribute("attachList",attachList);
        model.addAttribute("link",link);
        model.addAttribute(NAV_BAR,CURR_MAN_BAR);
        return "comptManFileUpload";
    }

    //添加附件
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public String uploadFile(@RequestParam String link,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
        //项目绝对路径
        String savePath = request.getServletContext().getRealPath(UploadConst.FILE_RES_PATH);
        List<String> fileNameList = FileUploader.uploadFile(request,response,savePath);
        for(String name : fileNameList){
            TComptAttchment tComptAttchment = new TComptAttchment();
            tComptAttchment.setCreatetime(new Date());
            tComptAttchment.setIsDelete(false);
            tComptAttchment.setName(name);
            tComptAttchment.setSkTCompt(new Integer(link));
            tComptAttchment.setType(true);//true :1 即为赛事附件
            tComptAttchment.setSavepath(UploadConst.FILE_URL_PATH.replaceAll("\\\\","\\\\\\\\")+name);
            comptAttchmentService.addOne(tComptAttchment);
        }
        return "redirect:/man/compt/list";
    }

    //删除附件
    @RequestMapping("/deleteFile")
    public @ResponseBody  String deleteFile(@RequestParam String link){
        if(comptAttchmentService.deleteOneById(link)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //编辑赛事
    @RequestMapping("/updateGet")
    public String updateGet(@RequestParam String link,Model model){
        TCompt compt = comptService.getComptById(link);
        List<TComptAttchment> attachList = comptAttchmentService.getAllItemsBySkId(link);
        model.addAttribute("attachList",attachList);
        model.addAttribute("compt",compt);
        model.addAttribute("isSubmit","success");
        model.addAttribute("link",link);
        model.addAttribute(NAV_BAR,CURR_PUB_BAR);
        model.addAttribute("status",compt.getStatus());//当前状态字
        return  "comptManPub";
    }

    @RequestMapping("/updateEditGet")
    public String updateEditGet(@RequestParam String link,Model model){
        TCompt compt = comptService.getComptById(link);
        List<TComptAttchment> attachList = comptAttchmentService.getAllItemsBySkId(link);
        model.addAttribute("attachList",attachList);
        model.addAttribute("compt",compt);
        model.addAttribute("link",link);
        model.addAttribute(NAV_BAR,CURR_PUB_BAR);
        model.addAttribute("status",compt.getStatus());//当前状态字
        System.out.println("上传：" + compt.getNeedFileIntro());

        return "comptManPub";
    }

    @RequestMapping("/updateStatus/{link}/{code}")
    public String updateStatus(@PathVariable String link,@PathVariable int code){
        if(link==""||link==null || code == 0 ){
            return "redirect:/man/compt/list/1";//重定向
        }
        comptService.updateStatus(link,code);//do update status
        return "redirect:/man/compt/list/1";//重定向
    }

    @RequestMapping("/delete")
    public @ResponseBody String delete(@RequestParam String link){
        if(comptService.deleteComptById(link)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //Ajax 置顶/取消置顶 接收参数type 0为取消置顶 1为置顶
    @RequestMapping("/manTotop/{value}")
    public @ResponseBody String manTotop(@PathVariable int value,@RequestParam String link){
        if(!"".equals(link)){
            TCompt compt = comptService.getComptById(link);
            if(1==value){
                compt.setCreatetime(new Date());
                compt.setIsTop(true);
            }else{
                compt.setIsTop(false);
            }
            comptService.updateCompt(compt);
        }
        return StringConst.AJAX_SUCCESS;
    }
}
