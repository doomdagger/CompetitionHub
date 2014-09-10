package com.djtu.signExam.action.user;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.UUIDGenerator;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.model.TComptAttachment;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.compt.ComptSigninService;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.DaoUtil;
import com.djtu.signExam.util.SessionConst;
import com.djtu.signExam.util.SessionUtil;
import com.djtu.signExam.util.StringConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JOECHOW on 2014/9/3.
 */

@Controller
@RequestMapping("/student")
public class StudentAccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private ComptService comptService;
    @Autowired
    private ComptSigninService signinService;


    private static final String NAV = "navbar";
    private static final String IDX = "idx";
    private static final String MYC = "myc";

    @RequestMapping(value = {"","/","/index"})
    public String index(HttpServletRequest request,HttpServletResponse response,Model model){
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        TUserStudent student = userService.getStudentInfo(userId.toString());
        model.addAttribute("student",student);
        DaoUtil.printEntity(student);
        return "userStudent";
    }


    /**
     * 显示当前学生报名赛事一览
     * @return
     */
    @RequestMapping(value = {"/myCompetition"})
    public String myCompetition(HttpServletRequest request,Model model){
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        //get pageable
        Pageable pageable = Pageable.inPage(1, ProjectPageConfig.SIGN_LIST_PAGESIZE);
        pageable.setPageCount(signinService.getPageCount(ProjectPageConfig.SIGN_LIST_PAGESIZE,userId.toString()));
        List<TSignin> signinList = signinService.getCompetitionByUserIdInPage(userId.toString(), pageable);
        model.addAttribute("signList",signinList);
        model.addAttribute("pageable",pageable);
        model.addAttribute(NAV,MYC);
        return "stuMyCompetition";
    }

    /**
     * 显示当前学生报名赛事一览InPage
     * @return
     */
    @RequestMapping(value = {"/myCompetition/{currpage}"})
    public String myCompetitionInPage(@PathVariable int currpage,HttpServletRequest request,Model model){
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        //get pageable
        Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.SIGN_LIST_PAGESIZE);
        pageable.setPageCount(signinService.getPageCount(ProjectPageConfig.SIGN_LIST_PAGESIZE,userId.toString()));
        List<TSignin> signinList = signinService.getCompetitionByUserIdInPage(userId.toString(), pageable);
        model.addAttribute("signList",signinList);
        model.addAttribute("pageable",pageable);
        model.addAttribute(NAV,MYC);
        return "stuMyCompetition";
    }

    //显示参赛概况
    @RequestMapping("/showCompetition")
    public String showCompetition(@RequestParam String link,HttpServletRequest request,Model model){
        Integer studentId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        //控制link是否被
        if(!signinService.checkSignInExistByIdByStuId(link,studentId.toString())){
            return "/noPermission";//如果该赛事不属于该同学，则会跳转至noPermission
        }
        //link 是signin的ID
        TSignin signin = signinService.getSignInById(link);
        String teamNo = signin.getTeamNo();
        Integer comptId = signin.getSkTCompt();
        List<TSignin> signinList = signinService.getAllSignIn(teamNo,comptId);
        TCompt compt = comptService.getComptById(comptId.toString());
        //model
        model.addAttribute(NAV,MYC);
        model.addAttribute("isLeader",signin.getIsLeader());
        model.addAttribute("compt",compt);
        model.addAttribute("signList",signinList);
        model.addAttribute("clink",signin.getSkTCompt());//当前赛事
        model.addAttribute("slink",signin.getSkTStudent());//当前学生
        return  "stuShowCompetition";
    }

    @RequestMapping("/signIn")
    public String signIn(String edit,@RequestParam String link,HttpServletRequest request,HttpServletResponse response,Model model){
        //确认赛事存在
        if(link ==null ||link == ""){
            return "redirect:/status/404";
        }
        //避免重复报名
        String slink = ((Integer) SessionUtil.getValue(request, SessionConst.U_USER, SessionConst.U_USER_LINK)).toString();
        if (signinService.checkSignInExist(link, slink)) {
            return "redirect:/noPermission";
        }

        /*Integer userId = (Integer) SessionUtil.getValue(request, SessionConst.U_USER, SessionConst.U_USER_LINK);*/
        //get data
        TUserStudent student = userService.getStudentInfo(slink!=null?slink.toString():"");
        TCompt compt = comptService.getComptById(link);
        //add to model
        model.addAttribute("student",student);
        model.addAttribute("compt",compt);
        model.addAttribute("link",link);
        model.addAttribute("slink",slink);
        if(edit == null || edit.trim() == ""){
            model.addAttribute("next","go");
        }
        return "comSignIn";
    }


    //检查是否已经报名或者已经添加
    /**
     *
     * @param link:赛事ID
     * @param slink：参赛者ID
     * @return:存在返回 success 不存在返回fail
     */
    @RequestMapping("/checkSignExist")
    public @ResponseBody
    String checkSignInExist(@RequestParam String link,String slink,HttpServletRequest request,HttpServletResponse response){
        if(slink == null || slink == ""){
            slink = ((Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK)).toString();
        }
        if(signinService.checkSignInExist(link,slink)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //第一步，先确认参加比赛,组长创建比赛
    @RequestMapping(value = "/confirmSignin",method = RequestMethod.POST)
    public String confirmSignin(@RequestParam String link,@RequestParam String slink,@RequestParam String email,@RequestParam String cellphone,@RequestParam String creditCard,String isHelpCredit){
        if(link == "" || slink == ""){
            return "/noPermission";
        }
        TUserStudent student = userService.getStudentInfo(slink);
        TCompt tCompt = comptService.getComptById(link);
        //new signin
        TSignin signin = new TSignin();
        signin.setSkTCompt(new Integer(link));//赛事ID
        signin.setSkTStudent(new Integer(slink));//组长ID
        signin.setComptName(tCompt.getTitle());//赛事名称
        signin.setEmail(email);//邮箱
        signin.setIsLeader(true);//设定组长
        signin.setCellphone(cellphone);
        signin.setIsHelpCredit(true);
        signin.setNumber(student.getUserNo());
        signin.setIsReward(false);
        signin.setIsValid(false);
        signin.setName(student.getUsername());//名字
        signin.setIsDelete(false);
        signin.setCreatetime(new Date());
        signin.setTeamNo(UUIDGenerator.randomUUID());//每个队伍的唯一标识
        //如果没有勾选，isHelpCredit不一定存在
        signin.setIsHelpCredit( (isHelpCredit!=null&&isHelpCredit=="on")?true:false);
        signin.setCreditCard(creditCard);
        signinService.addTeamMember(signin);
        //参赛报名，提交后跳转 我的赛事
        return "redirect:/student/myCompetition";
    }

    //第二步，添加组员(我的赛事中也能进行此操作)
    @RequestMapping("/addTeamMemberGet")
    public String addTeamMemberGet(@RequestParam String clink,@RequestParam String team,Model model){
        List<TSignin> memberList = signinService.getAllSignIn(team,new Integer(clink));
        model.addAttribute("memberList",memberList);//成员列表
        model.addAttribute(NAV,MYC);
        model.addAttribute("clink",clink);//赛事ID
        return "stuAddTeamMember";
    }

    @RequestMapping("/addTeamMemberPost")
    public String addTeamMemberPost(){
        //注意isLeader是false
        return "";
    }

    //根据成员在 TSignin 中的ID
    @RequestMapping("/deleteTeamMember")
    public @ResponseBody String deleteTeamMember(@RequestParam String link){
        //删除成员
        if(signinService.quitFromCompetitionByUserId(link)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //第三步，上传作品(我的赛事中也能进行此操作)
    @RequestMapping("/addAttachMentGet")
    public String addAttachMentGet(@RequestParam String clink,@RequestParam String slink,Model model){
        TSignin sign = signinService.getSignInByComIdByStuId(clink,slink);
        List<TComptAttachment> attachmentList = signinService.getAllAttachment(clink,sign.getTeamNo());//赛事ID 和teamNo
        model.addAttribute(NAV,MYC);
        model.addAttribute("attList",attachmentList);//附件列表
        return "stuAddAttachMent";
    }

    @RequestMapping("/addAttachMentPost")
    public String addAttachMentPost(HttpServletRequest request){
        //存的时候根据teamNo和sk_compt
        String URI = request.getRequestURI();
        //return "redirect:"+URI;
        return "redirect:/student/myCompetition/";
    }

    //根据attachment的ID
    @RequestMapping("/deleteAttach")
    public @ResponseBody String deleteAttach(@RequestParam String link){
        if(signinService.deleteAttachMent(link)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }
}
