package com.djtu.signExam.action.user;

import com.djtu.signExam.dao.support.UUIDGenerator;
import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.RegisterAndLoginService;
import com.djtu.signExam.util.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by root on 14-7-7.
 */

@Controller
@RequestMapping("/user")
public class RegisterAndLoginController {

    @Autowired
    public RegisterAndLoginService registerAndLoginService;

    @RequestMapping("/regGet")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/checkEmail",method = RequestMethod.GET)
    public @ResponseBody String checkEmail(@RequestParam String email){
        //System.out.println(email);
        if(registerAndLoginService.checkEmail(email)){
            return StringConst.AJAX_SUCCESS;
        }else{
            return StringConst.AJAX_FAIL;
        }
    }

    //发送验证码至邮箱
    @RequestMapping("/sendCode")
    public @ResponseBody String sendCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String email){
        String vc = MyMailUtil.generateNum();//4位数验证码
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SessionConst.REG_SESSION_INTERVAL);//session生存时间
        session.setAttribute(SessionConst.REG_VALIDCODE,vc);
        //send email
        if(MyMailUtil.simpleSendMail(MyMailConst.MAIL_REGISTER_TITLE,MyMailConst.MAIL_REGISTER_CONTENT+vc,email)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //验证邮箱
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    public @ResponseBody String checkCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String link){
        HttpSession session = request.getSession();
        System.out.println("link:"+link+"session:"+session.getAttribute(SessionConst.REG_VALIDCODE));
        if(link.trim().equals(session.getAttribute(SessionConst.REG_VALIDCODE.trim()))){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    @RequestMapping(value = "/regPost", method = RequestMethod.POST)
    public String regPost(@RequestParam String username,@RequestParam String email,@RequestParam String studentNo,@RequestParam String pwd,
                          @RequestParam String academy,@RequestParam String pro,@RequestParam String cellphone,HttpServletRequest request,RedirectAttributes attr){

        String ValidCode = UUIDGenerator.randomUUID();
        TUserStudent tUserStudent = new TUserStudent();
        tUserStudent.setUsername(username);
        tUserStudent.setEmail(email);
        tUserStudent.setUserPwd(pwd);
        tUserStudent.setIsActive(false);
        tUserStudent.setUserNo(studentNo);
        tUserStudent.setActivateCode(ValidCode);
        tUserStudent.setCellphone(cellphone);
        tUserStudent.setAcademy(academy);
        tUserStudent.setProfession(pro);
        tUserStudent.setIsDelete(false);
        if(registerAndLoginService.register(tUserStudent)){
            //设置session
        }
        //return "reg_valid";
        attr.addAttribute("email",email);
        return "redirect:/user/waitValidEmail";//重定向，避免刷新提交
    }

    @RequestMapping("/waitValidEmail")
    public String sendEmail(@RequestParam String email,Model model){
        model.addAttribute("email",email);
        return "reg_valid";
    }



    //login and set the login session
    @RequestMapping(value = "/indexLoginStudent",method = RequestMethod.POST)
    public @ResponseBody String indexLoginStudent(HttpServletRequest request,@RequestParam String username,@RequestParam String pwd){
        TUserStudent student = new TUserStudent();
        if(username.indexOf("@")<0 || username.indexOf(".com")<0){
            //is not the email
            //type:0:学生帐号 1：管理员帐号 2：学院帐号 3：教务帐号
             student = (TUserStudent) registerAndLoginService.signInByNoAndReturnObj(username,pwd,0);
        }else{
            student = (TUserStudent) registerAndLoginService.signInByEmailAndReturnObj(username,pwd,0);
        }
        if(student != null){
            //Set the login session
            HttpSession session = request.getSession();
            HashMap<String,Object> userSession = new HashMap<String, Object>();
            HashMap<String,Object> userMap = new HashMap<String, Object>();
            userMap.put(SessionConst.U_USER_TYPE,0);//用户类型
            userMap.put(SessionConst.U_USER_NAME,student.getUsername());//用户姓名
            userMap.put(SessionConst.U_USER_LINK,student.getID());//用户ID

            //put in user session
            session.setAttribute(SessionConst.U_LOGIN,SessionConst.U_LOGIN_SUCC);//登录标志
            session.setAttribute(SessionConst.U_USER,userMap);
            //return json
            JSONObject result = new JSONObject();
            result.put("usernameSucc",student.getUsername()==null?"":student.getUsername());
            result.put("usertypeSucc",0);
            result.put("result",StringConst.AJAX_SUCCESS);
            return result.toString();
        }
        JSONObject result = new JSONObject();
        result.put("result",StringConst.AJAX_FAIL);
        return result.toString();
    }

    //管理员后台登录
    @RequestMapping(value = "/indexLoginAdmin")
    public @ResponseBody String indexLoginAdmin(HttpServletRequest request,@RequestParam String username,@RequestParam String pwd){
        TUserAdmin tUserAdmin = registerAndLoginService.signInByEmail(username,pwd);//admin login
        if(tUserAdmin != null){
            //Set the login session
            HttpSession session = request.getSession();
            HashMap<String,Object> userSession = new HashMap<String, Object>();
            HashMap<String,Object> userMap = new HashMap<String, Object>();
            userMap.put(SessionConst.U_USER_TYPE,tUserAdmin.getType());//用户类型
            userMap.put(SessionConst.U_USER_NAME,tUserAdmin.getName());//用户姓名
            userMap.put(SessionConst.U_USER_LINK,tUserAdmin.getID());//用户ID
            userMap.put(SessionConst.U_USER_ISTP,tUserAdmin.getIsSuper());
            System.out.println("LOGIN-ISTP:"+tUserAdmin.getIsSuper());
            //put in user session
            session.setAttribute(SessionConst.U_LOGIN,SessionConst.U_LOGIN_SUCC);//登录标志
            session.setAttribute(SessionConst.U_USER,userMap);
            //return json
            JSONObject result = new JSONObject();
            result.put("usernameSucc",tUserAdmin.getName()==null?"":tUserAdmin.getName());
            result.put("usertypeSucc",tUserAdmin.getType());//账号类型
            result.put("result",StringConst.AJAX_SUCCESS);
            return result.toString();
        }
        JSONObject result = new JSONObject();
        result.put("result",StringConst.AJAX_FAIL);
        return result.toString();
    }

    //注销登录
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(SessionConst.U_LOGIN,SessionConst.U_LOGIN_FAIL);
        httpSession.setAttribute(SessionConst.U_USER,null);//设置用户为空
        return "redirect:/index";
    }

    //检查登录状态
    @RequestMapping("/checkLogin")
    public @ResponseBody String checkLogin(HttpServletRequest request){
        Object isLogin = SessionUtil.getValue(request,SessionConst.U_LOGIN);
        if(isLogin != null && (Integer)isLogin == SessionConst.U_LOGIN_SUCC){
            return  StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //检查登录者类型
    //失败返回 fail 成功返回userType
    @RequestMapping("/checkLoginType")
    public @ResponseBody String checkLoginType(HttpServletRequest request){
        Object isLogin = SessionUtil.getValue(request,SessionConst.U_LOGIN);
        if(isLogin != null && (Integer)isLogin == SessionConst.U_LOGIN_SUCC){
            Object userType = SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_TYPE);
            return (String) userType;
        }else{
            return StringConst.AJAX_FAIL;
        }
    }
}
