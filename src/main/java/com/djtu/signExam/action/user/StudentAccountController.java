package com.djtu.signExam.action.user;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.UUIDGenerator;
import com.djtu.signExam.model.*;
import com.djtu.signExam.service.compt.ComptAttchmentService;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.service.compt.ComptSigninService;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    @Autowired
    private ComptAttchmentService attchmentService;


    private static final String NAV = "navbar";
    private static final String IDX = "idx";
    private static final String MYC = "myc";
    private static final String MDPD = "mdpd";

    @RequestMapping(value = {"","/","/index"})
    public String index(HttpServletRequest request,HttpServletResponse response,Model model){
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        TUserStudent student = userService.getStudentInfo(userId.toString());
        model.addAttribute("student",student);
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
        //pageable.setPageCount(signinService.getPageCount(ProjectPageConfig.SIGN_LIST_PAGESIZE,userId.toString()));
        List<TSignin> signinList = signinService.getCompetitionByUserIdInPage(userId.toString(), pageable);
        //String userEmail = userService.getStudentInfo(userId.toString()).getEmail();
        //signinList = signinList==null?signinService.getCompetitionByUserEmailInPage(userEmail,pageable):signinList;
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
        //compt
        TCompt compt = comptService.getComptById(comptId.toString());
        //Attach
        TComptAttachment attach = attchmentService.getAttachByTeamNo(teamNo);
        //model
        model.addAttribute(NAV,MYC);
        model.addAttribute("isLeader",signin.getIsLeader());
        model.addAttribute("compt",compt);
        model.addAttribute("attach",attach);
        model.addAttribute("signList",signinList);
        model.addAttribute("teamNo",teamNo);
        model.addAttribute("clink",signin.getSkTCompt());//当前赛事
        model.addAttribute("slink",signin.getSkTStudent());//当前学生
        model.addAttribute("link",link);
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
        signin.setMemberNum(1);
        signin.setIsPass(false);
        signin.setIsPriority(false);
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



    //modify password
    @RequestMapping("/modifyPassword")
    public String modifyPassword(HttpServletRequest request,Model model){
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        TUserStudent student = userService.getStudentInfo(userId.toString());
        model.addAttribute("user",student);
        model.addAttribute(NAV,MDPD);
        return "stuModifyPassword";
    }

    @RequestMapping("/doModifyPassword")
    public String doModifyPassword(){
        return "";
    }

    //发送验证码至邮箱,修改密码的邮箱验证
    @RequestMapping("/validEmail")
    public @ResponseBody
    String sendCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String email){
        String vc = MyMailUtil.generateNum();//4位数验证码
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SessionConst.REG_SESSION_INTERVAL);//session生存时间
        session.setAttribute(SessionConst.MODIFY_PASSWORD,vc);
        //send email
        if(MyMailUtil.simpleSendMail(MyMailConst.MODIFY_PWD_TITLE,MyMailConst.MODIFY_PWD_CONTENT+vc,email)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //验证邮箱
    @RequestMapping(value = "/validEmailCode", method = RequestMethod.POST)
    public @ResponseBody String checkCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String link){
        HttpSession session = request.getSession();
        //System.out.println("link:"+link+"session:"+session.getAttribute(SessionConst.ADD_ACCOUNT_CODE));
        if(link.trim().equals(session.getAttribute(SessionConst.MODIFY_PASSWORD.trim()))){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    @RequestMapping("/resetPassword")
    public @ResponseBody String resetPassword(HttpServletRequest request,@RequestParam String link){
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        if(link==null || link==""){
            return StringConst.AJAX_FAIL;
        }
        if(userService.resetStudentPassword(userId.toString(),link)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }


    /**
     * 退出比赛选项
     * @param link:当前t_signin的ID
     */
    @RequestMapping(value = "/quitCompetition")
    public @ResponseBody String quitCompetition(HttpServletRequest request,@RequestParam String link){
        TSignin signin = signinService.getSignInById(link);
        if(signin.getIsLeader()){
            String teamNo = signin.getTeamNo();
            return signinService.quitFromCompetitionByTeamNo(teamNo)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
        }else{
            return signinService.quitFromCompetitionByUserId(link)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
        }
    }

    /**
     * 添加小组成员
     * link是tsignin的id，此为组长的tsignin
     */
    @RequestMapping(value = "addTeamMember",method = RequestMethod.POST)
    public @ResponseBody String addTeamMember(@RequestParam String name,@RequestParam String NO,@RequestParam String email,
                                              @RequestParam String cellphone,@RequestParam Integer isHelp,@RequestParam String link){
        TSignin originSignin = signinService.getSignInById(link);
        //根据email判断该用户是否注册

        if(signinService.isExistAnEmailInTeamNo(originSignin.getTeamNo(),email)){
            //如果要添加的emial在一个teamNo中已经重复的话，则添加失败
            return StringConst.AJAX_FAIL;
        }

        //new member
        TSignin signin = new TSignin();
        signin.setComptName(originSignin.getComptName());
        signin.setSkTCompt(originSignin.getSkTCompt());
        signin.setIsValid(originSignin.getIsValid());
        signin.setTeamNo(originSignin.getTeamNo());
        signin.setIsLeader(false);
        signin.setIsDelete(false);
        signin.setCreatetime(new Date());
        signin.setCellphone(cellphone);
        signin.setEmail(email);
        signin.setName(name);
        signin.setNumber(NO);
        signin.setIsHelpCredit(isHelp==1?true:false);
        TUserStudent student = userService.getStudentInfoByEmail(email);
        if (student!=null){
            //如果该用户为已经注册的用户，则更新tsignin中的用户ID
            signin.setSkTStudent(student.getID());//更新ID
        }
        //add team NO
        if(signinService.addTeamMember(signin)){
            originSignin.setMemberNum(originSignin.getMemberNum()+1);
            signinService.updateInfo(originSignin);
            return StringConst.AJAX_SUCCESS;
        }else{
            return StringConst.AJAX_FAIL;
        }
    }

    /**
     * 获取要编辑的组员的信息
     * link为组员tsignin的ID
     */
    @RequestMapping(value = "/getEditMemberInfo")
    public @ResponseBody String getEditMemberInfo(@RequestParam String link){
        TSignin signin = signinService.getSignInById(link);
        JSONObject json = new JSONObject();
        if(signin!=null){
            json.put("data",new JSONObject(signin));
            json.put("result",StringConst.AJAX_SUCCESS);
        }else{
            json.put("data","");
            json.put("result",StringConst.AJAX_FAIL);
        }
        return json.toString();
    }

    @RequestMapping(value = "/confirmEditMember")
    public @ResponseBody String confirmEditMember(@RequestParam String name,@RequestParam String NO,@RequestParam String email,
                                                  @RequestParam String cellphone,@RequestParam Integer isHelp,@RequestParam String creditCard,
                                                  @RequestParam String link,@RequestParam String originEmail,@RequestParam String signLink){

        TSignin originSignin = signinService.getSignInById(link);
        //根据email判断该用户是否注册

        //如果先email跟原email不相同的话
        if(!email.equals(originEmail)){
            if(signinService.isExistAnEmailInTeamNo(originSignin.getTeamNo(),email)){
                //如果要添加的emial在一个teamNo中已经重复的话，则添加失败
                return StringConst.AJAX_FAIL;
            }
        }
        //new member
        TSignin signin = signinService.getSignInById(signLink);
        signin.setCellphone(cellphone);
        signin.setEmail(email);
        signin.setName(name);
        signin.setNumber(NO);
        signin.setIsHelpCredit(isHelp==1?true:false);
        TUserStudent student = userService.getStudentInfoByEmail(email);
        //根据新的Email来绑定用户ID
        if (student!=null){
            //如果该用户为已经注册的用户，则更新tsignin中的用户ID
            signin.setSkTStudent(student.getID());//更新ID
        }
        return signinService.updateInfo(signin)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
    }

    /**
     * 上传作品
     * @RequestParam String link,
     * @RequestParam String teamNo,
     * @RequestParam String needFileIntro,
     */
    @RequestMapping(value = "/uploadWorks",method = RequestMethod.POST)
    public String uploadWorks(HttpServletRequest request,HttpServletResponse response,
                              RedirectAttributes redirectAttributes){
        String teamNo = request.getParameter("teamNo");
        String needFileIntro = request.getParameter("needFileIntro");
        try {
            needFileIntro = URLDecoder.decode(needFileIntro, "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String link = request.getParameter("link");
//        System.out.println("teamNo: "+teamNo+" intro: "+needFileIntro+" link: "+link);
        /*System.out.println("form data: "+request.getParameterNames());
        while (request.getParameterNames().hasMoreElements()){
            System.out.println(request.getParameterNames().nextElement());
        }*/
        //项目绝对路径
        String savePath = request.getServletContext().getRealPath(UploadConst.WORKS_RES_PATH+teamNo+File.separator);//这里中间要加上teamNo
        List<String> fileNameList = FileUploader.uploadFile(request,response,savePath);
        TComptAttachment attachment = new TComptAttachment();
        String key = attchmentService.isAttachExistInTeamNoAndReturnKey(teamNo);
        if( !"".equals(key) ){
            //update
            attachment.setID(new Integer(key));
            attachment.setName(fileNameList.get(0));
            attachment.setSavepath((UploadConst.WORKS_URL_PATH+teamNo+File.separator).replaceAll("\\\\","\\\\\\\\")+fileNameList.get(0));
            attachment.setCreatetime(new Date());
            attachment.setIntroduction(needFileIntro);
            attchmentService.updateOne(attachment);
        }else{
            //add
            attachment.setIsValid(false);
            attachment.setSkTCompt(new Integer(link));
            attachment.setTeamNo(teamNo);
            attachment.setCreatetime(new Date());
            attachment.setIsDelete(false);
            attachment.setSavepath((UploadConst.WORKS_URL_PATH+teamNo+File.separator).replaceAll("\\\\","\\\\\\\\")+fileNameList.get(0));
            attachment.setName(fileNameList.get(0));
            attachment.setSkTSignup(0);
            attachment.setIntroduction(needFileIntro);
            attchmentService.addOne(attachment);
        }
        //after add an attachment,redirect to the show page
        redirectAttributes.addAttribute("link",link);
        return "redirect:/student/showCompetition";
    }

    /**
     * delete the attach by attach_id
     */
    @RequestMapping(value = "/deleteAttachMent")
    public @ResponseBody String deleteAttachMent(@RequestParam String link){
        return attchmentService.deleteOneById(link)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
    }
}
