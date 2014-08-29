package com.djtu.signExam.action.user;

import com.djtu.signExam.dao.support.UUIDGenerator;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.RegisterAndLoginService;
import com.djtu.signExam.service.user.impl.RegisterAndLoginServiceImpl;
import com.djtu.signExam.util.MailUtil;
import com.djtu.signExam.util.SessionUtil;
import com.djtu.signExam.util.StringConst;
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

    @RequestMapping(value = "/regPost", method = RequestMethod.POST)
    public String regPost(@RequestParam String username,@RequestParam String email,@RequestParam String studentNo,@RequestParam String pwd,
                          @RequestParam String academy,@RequestParam String pro,@RequestParam String cellphone,RedirectAttributes attr){

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

        registerAndLoginService.register(tUserStudent);
        //send email
        try {
            MailUtil.sendHTMLMessage(SessionUtil.getSession(),"787449527@qq.com","787449527@qq.com","","","title","content");
        } catch (MessagingException e) {
            e.printStackTrace();
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

    @RequestMapping(value = "/reSendEmail", method = RequestMethod.GET)
    public String reSendEmail(@RequestParam String email,RedirectAttributes attr){
        attr.addAttribute("email",email);
        return "redirect:/user/waitValidEmail";//重定向，避免刷新提交
    }
}
