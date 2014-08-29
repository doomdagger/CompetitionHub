package com.djtu.signExam.action.user;

import com.djtu.signExam.dao.support.UUIDGenerator;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.RegisterAndLoginService;
import com.djtu.signExam.service.user.impl.RegisterAndLoginServiceImpl;
import com.djtu.signExam.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by root on 14-7-7.
 */

@Controller
@RequestMapping("user")
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
        tUserStudent.setUUsername(username);
        tUserStudent.setAEmail(email);
        tUserStudent.setAUserPwd(pwd);
        tUserStudent.setAIsActive(false);
        tUserStudent.setAUserNo(studentNo);
        tUserStudent.setAActivateCode(ValidCode);
        tUserStudent.setUCellphone(cellphone);
        tUserStudent.setUProfession(pro);
        tUserStudent.setAIsDelete(false);
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



    
}
