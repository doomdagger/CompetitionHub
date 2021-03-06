package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TComptAttachment;
import com.djtu.signExam.service.compt.ComptAttchmentService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JOECHOW on 2014/8/21.
 * 学院对赛事后台管理和操作
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
    public String list(HttpServletRequest request,Model model){

        //int pageCount = comptService.getPageCount(ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        Pageable pageable = Pageable.inPage(1,ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        //get current user info
        Integer user_id = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        List<TCompt> comptList = comptService.getComByPage(user_id.toString(),pageable);
        //pageable.setPageCount(pageCount);
        model.addAttribute("pageable",pageable);
        model.addAttribute("comptList",comptList);
        model.addAttribute(NAV_BAR,CURR_MAN_BAR);
        return "comptManList";
    }

    @RequestMapping("/list/{currpage}")
    public String listInPage(HttpServletRequest request,@RequestParam int currpage,Model model){
        //get pageable
        //int pageCount = comptService.getPageCount(ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        //currpage = (currpage<1 || currpage> pageCount)?1:currpage;
        //get current user info
        Integer user_id = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.MAN_COMP_LIST_PAGESIZE);
        //pageable.setPageCount(pageCount);
        //get data
        List<TCompt> comptList = comptService.getComByPage(user_id.toString(),pageable);
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
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes){
        //get current user info
        Integer user_id = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        String user_name = (String) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_NAME);
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
        compt.setCreatetime(new Date());
        compt.setIsVaild(currentStatus < 2 ? false : true);
        compt.setIsTop(false);
        compt.setSkTUserAdmin(user_id);
        compt.setAdminName(user_name);
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
        List<TComptAttachment> attachList = comptAttchmentService.getAllItemsBySkId(link);
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
        if(fileNameList.size() < 1){
        	for(String name : fileNameList){
                TComptAttachment tComptAttachment = new TComptAttachment();
                tComptAttachment.setCreatetime(new Date());
                tComptAttachment.setIsDelete(false);
                tComptAttachment.setName(name);
                tComptAttachment.setSkTCompt(new Integer(link));
                tComptAttachment.setType(true);//true :1 即为赛事附件
                tComptAttachment.setSavepath(UploadConst.FILE_URL_PATH.replaceAll("\\\\","\\\\\\\\")+name);
                comptAttchmentService.addOne(tComptAttachment);
            }
        }
        redirectAttributes.addAttribute("link",link);
        return "redirect:/man/compt/uploadGet";
    }
    
    //附件上传-2014-11-17
    @RequestMapping(value = "/uploadAttach",method = RequestMethod.POST)
    public String uploadAttach(@RequestParam String link,HttpServletRequest request,HttpServletResponse response,@RequestParam MultipartFile upFile,RedirectAttributes redirectAttributes) throws IOException{
    	
    	String savePath = request.getServletContext().getRealPath(UploadConst.FILE_RES_PATH);
    	
    	if (!new File(savePath).isDirectory())
        {
        	new File(savePath).mkdirs();
        }
    	
    	//获取文件输出流
        System.out.println("文件大小："+upFile.getSize());
        
        if(upFile.getSize() > UploadConst.COMMON_MAX_FILESIZE){
        	response.setHeader("text/html","UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("上传文件大小超出10M");
            return null;
        }
        
        String urlPath = "";
        try {
        	//获取上传文件旧名
            String name = upFile.getOriginalFilename();
            //获取后缀名
            //String suffix = name.substring(name.lastIndexOf(".")+1);
            //创建上传目标文件
            //String newFileName = "COVER"+System.currentTimeMillis() + new Random(50000).nextInt() + "."+suffix;
            File targetFile = new File(savePath,name);
            //获得保存数据库的http访问路径
            urlPath = UploadConst.FILE_URL_PATH.replaceAll("\\\\", "/")+name;
            //创建输入输出流
        	InputStream is = upFile.getInputStream();
        	FileOutputStream fos = new FileOutputStream(targetFile);
        	FileCopyUtils.copy(is, fos);
			//打开上传图片文件夹，调试用
			//Runtime.getRuntime().exec("cmd /c start "+savePath);
        	
        	
        	//add to 数据库
        	TComptAttachment tComptAttachment = new TComptAttachment();
            tComptAttachment.setCreatetime(new Date());
            tComptAttachment.setIsDelete(false);
            tComptAttachment.setName(name);
            tComptAttachment.setSkTCompt(new Integer(link));
            tComptAttachment.setType(true);//true :1 即为赛事附件
            tComptAttachment.setSavepath(urlPath);
            comptAttchmentService.addOne(tComptAttachment);
            
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("上传文件失败");
			response.setHeader("text/html","UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("上传文件失败");
		}
        redirectAttributes.addAttribute("link",link);
        return "redirect:/man/compt/uploadGet";
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
        List<TComptAttachment> attachList = comptAttchmentService.getAllItemsBySkId(link);
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
        List<TComptAttachment> attachList = comptAttchmentService.getAllItemsBySkId(link);
        model.addAttribute("attachList",attachList);
        model.addAttribute("compt",compt);
        model.addAttribute("link",link);
        model.addAttribute(NAV_BAR,CURR_PUB_BAR);
        model.addAttribute("status",compt.getStatus());//当前状态字
        return "comptManPub";
    }

    @RequestMapping("/updateStatus/{link}/{code}")
    public String updateStatus(@PathVariable String link,@PathVariable int code){
        if("".equals(link) || code == 0 ){
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
    	boolean isSucc = false;
        if(!"".equals(link)){
            //TCompt compt = comptService.getComptById(link);
            if(1==value){
                //compt.setCreatetime(new Date());
                //compt.setIsTop(true);
            	isSucc = comptService.setToTop(link, true);
            }else{
            	isSucc = comptService.setToTop(link, false);
            }
            //comptService.updateCompt(compt);
        }
        return isSucc?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
    }

    @RequestMapping(value = "/updateStatusByAjax",method = RequestMethod.POST)
    public @ResponseBody String updateStatusByAjax(String link,int status,HttpServletRequest request){
        if(!"".equals(link) && comptService.updateStatus(link,status)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    @RequestMapping("/confirmResult")
    public String confirmResult(String link,Model model){
        TCompt compt = comptService.getComptById(link);
        model.addAttribute("compt", compt);
        model.addAttribute("link",link);
        return "comptManConfirmResult";
    }
}
