package com.djtu.signExam.action.user;

import com.djtu.signExam.model.TCompt;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

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
    public String myCompetition(Model model){

        return "stuMyCompetition";
    }

    //显示参赛概况
    @RequestMapping("/showCompetition")
    public String showCompetition(){
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

    //第一步，先确认参加比赛
    @RequestMapping(value = "/confirmSignin",method = RequestMethod.POST)
    public String confirmSignin(String edit,@RequestParam String link,@RequestParam String slink,@RequestParam String email,@RequestParam String cellphone,@RequestParam String creditCard,String isHelpCredit){
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
        signin.setIsLeader(true);
        signin.setCellphone(cellphone);
        signin.setIsHelpCredit(true);
        signin.setNumber(student.getUserNo());
        signin.setIsReward(false);
        signin.setIsValid(false);
        signin.setName(student.getUsername());//名字
        signin.setIsDelete(false);
        //如果没有勾选，isHelpCredit不一定存在
        signin.setIsHelpCredit( (isHelpCredit!=null&&isHelpCredit=="on")?true:false);
        signin.setCreditCard(creditCard);
        signinService.addTeamMember(signin);
        if(edit != ""){
            //如果是编辑，重新提交后跳转到显示页面
            return "redirect:/student/showCompetition";
        }
        //参赛报名，提交后跳转到下一步
        return "redirect:/student/addTeamMemberGet";
    }

    //第二步，添加组员(我的赛事中也能进行此操作)
    @RequestMapping("/addTeamMemberGet")
    public String addTeamMemberGet(Model model){
        model.addAttribute("memberList","");//成员列表
        return "stuAddTeamMember";
    }

    @RequestMapping("/addTeamMemberPost")
    public String addTeamMemberPost(){
        return "";
    }

    //根据成员在 TSignin 中的ID
    @RequestMapping("/deleteTeamMember")
    public @ResponseBody String deleteTeamMember(@RequestParam String link){
        //删除成员
        signinService.quitFromCompetitionByUserId(link);
        return StringConst.AJAX_FAIL;
    }

    //第三步，上传作品(我的赛事中也能进行此操作)
    @RequestMapping("/addAttachMentGet")
    public String addAttachMentGet(){
        return "stuAddAttachMent";
    }

    @RequestMapping("/addAttachMentPost")
    public String addAttachMentPost(){
        return "";
    }

    //根据attachment的ID
    @RequestMapping("/deleteAttach")
    public @ResponseBody String deleteAttach(@RequestParam String link){
        return StringConst.AJAX_FAIL;
    }
}
