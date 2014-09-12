package com.djtu.signExam.action.user;

import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by JOECHOW on 2014/8/21.
 */

@Controller
@RequestMapping("/man")
public class ManController {

    private static final String NAV = "navbar";
    private static final String MDPD = "mdpd";
    @Autowired
    private UserService userService;

    //when get here shoud be access the "ManControllerInterceptor"
    @RequestMapping(value = {"","/","/index"})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        //should judge the user type here and return the correct page and data
        //HashMap<String,Object> user = (HashMap<String, Object>) request.getSession().getAttribute(SessionConst.U_USER);
        //Integer userId = user.get(SessionConst.U_USER_LINK)!=null?(Integer) user.get(SessionConst.U_USER_LINK):0;
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        TUserAdmin admin = userService.getAdminInfo(userId.toString());
        model.addAttribute("user",admin);
        return "userAdmin";
    }

    @RequestMapping("/modifyPassword")
    public String modifyPassword(HttpServletRequest request,Model model){
        Integer userId = (Integer) SessionUtil.getValue(request,SessionConst.U_USER,SessionConst.U_USER_LINK);
        TUserAdmin admin = userService.getAdminInfo(userId.toString());
        model.addAttribute("user",admin);
        model.addAttribute(NAV,MDPD);
        return "modifyPassword";
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
        if(userService.resetAdminPassword(userId.toString(),link)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }
}
