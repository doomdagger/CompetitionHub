package com.djtu.signExam.action.user;

import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.*;
import org.json.JSONObject;
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
import java.util.List;

/**
 * Created by JOECHOW on 2014/9/10.
 */
@Controller
@RequestMapping("/man/admin")
public class AdminAccountController {

    private final String NAV_BAR = "navbar";
    private final String CURR_BAR_ADMIN = "adminAccount";
    private final String CURR_BAR_ACADEMY = "academyAccount";
    private final String CURR_BAR_DJTU = "djtuAccount";

    @Autowired
    private UserService userService;

    @RequestMapping("/accountGet")
    public String accountGet(Model model){
        List<TUserAdmin> userList = userService.getAllAccountByType(StringConst.ADMIN_ACCOUNT);
        model.addAttribute("userList",userList);
        model.addAttribute(NAV_BAR,CURR_BAR_ADMIN);
        return "adminAccount";//管理员账号
    }

    @RequestMapping("/academyAccount")
    public String academyAccountGet(Model model){
        List<TUserAdmin> userList = userService.getAllAccountByType(StringConst.ACADE_ACCOUNT);
        model.addAttribute("userList",userList);
        model.addAttribute(NAV_BAR,CURR_BAR_ACADEMY);
        return "adminAcademyAccount";//学院账号
    }

    @RequestMapping("/djtuAccount")
    public String djtuAccountGet(Model model){
        List<TUserAdmin> userList = userService.getAllAccountByType(StringConst.DJTU_ACCOUNT);
        model.addAttribute("userList",userList);
        model.addAttribute(NAV_BAR,CURR_BAR_DJTU);
        return "adminDjtuAccount";//教务账号
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String addAccount(@RequestParam String name,@RequestParam String cellphone,@RequestParam String pwd,@RequestParam String email,@RequestParam int type,HttpServletRequest request,Model model){
        TUserAdmin admin = new TUserAdmin();
        admin.setIsDelete(false);
        admin.setEmail(email);
        admin.setType(type);
        admin.setPwd(pwd);
        admin.setIsActive(true);
        admin.setIsSuper(false);
        admin.setName(name);
        admin.setCellphone(cellphone);
        admin.setUsertype(new Integer(type).toString());
        if(userService.addAdminer(admin)){
            if(type==1){
                return "redirect:/man/admin/accountGet";
            }else if(type == 2){
                return "redirect:/man/admin/academyAccount";
            }else if(type == 3){
                return "redirect:/man/admin/djtuAccount";
            }else{
                model.addAttribute("message","操作失败");
                return "message";
            }
        }
        model.addAttribute("message","操作失败");
        return "message";
    }

    @RequestMapping("/checkEmailExist")
    public @ResponseBody String checkEmailExist(@RequestParam String email){
        if(userService.checkEmailExist(email)){
            return StringConst.AJAX_SUCCESS;//邮箱已经被使用
        }
        return StringConst.AJAX_FAIL;
    }

    //get account info
    @RequestMapping("/getAccountInfo")
    public @ResponseBody String getAccountInfo(@RequestParam String link){
        TUserAdmin admin = userService.getAdminInfo(link);
        JSONObject object = new JSONObject();
        if(admin==null){
            object.put("result",StringConst.AJAX_FAIL);
            return object.toString();
        }
        object.put("name",admin.getName());
        object.put("email",admin.getEmail());
        object.put("cellphone",admin.getCellphone());
        object.put("pwd",admin.getPwd());
        object.put("result",StringConst.AJAX_SUCCESS);
        return object.toString();
    }
    //更新编辑
    @RequestMapping(value = "/updateAccount",method = RequestMethod.POST)
    public String updateAccount(@RequestParam String name,@RequestParam String cellphone,@RequestParam String pwd,@RequestParam String email,@RequestParam int type,@RequestParam int link,HttpServletRequest request,Model model){
        TUserAdmin admin = new TUserAdmin();
        admin.setIsDelete(false);
        admin.setEmail(email);
        admin.setType(type);
        admin.setPwd(pwd);
        admin.setIsActive(true);
        admin.setIsSuper(false);
        admin.setName(name);
        admin.setCellphone(cellphone);
        admin.setUsertype(new Integer(type).toString());
        admin.setID(link);
        if(userService.updateAdminer(admin)){
            if(type==1){
                return "redirect:/man/admin/accountGet";
            }else if(type == 2){
                return "redirect:/man/admin/academyAccount";
            }else if(type == 3){
                return "redirect:/man/admin/djtuAccount";
            }else{
                model.addAttribute("message","操作失败");
                return "message";
            }
        }
        model.addAttribute("message","操作失败");
        return "message";
    }

    @RequestMapping("/deleteAccount")
    public @ResponseBody String deleteAccount(@RequestParam String link){
        TUserAdmin admin = userService.getAdminInfo(link);
        if(admin.getIsSuper()){
            return StringConst.AJAX_FAIL;//无法删除最高权限管理员
        }
        if(userService.removeAdminer(link)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //发送验证码至邮箱
    @RequestMapping("/sendCode")
    public @ResponseBody String sendCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String email){
        String vc = MyMailUtil.generateNum();//4位数验证码
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SessionConst.REG_SESSION_INTERVAL);//session生存时间
        session.setAttribute(SessionConst.ADD_ACCOUNT_CODE,vc);
        //send email
        if(MyMailUtil.simpleSendMail(MyMailConst.MAIL_ACCOUNT_TITLE,MyMailConst.MAIL_ACCOUNT_CONTENT+vc,email)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    //验证邮箱
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    public @ResponseBody String checkCode(HttpServletRequest request,HttpServletResponse response,@RequestParam String link){
        HttpSession session = request.getSession();
        //System.out.println("link:"+link+"session:"+session.getAttribute(SessionConst.ADD_ACCOUNT_CODE));
        if(link.trim().equals(session.getAttribute(SessionConst.ADD_ACCOUNT_CODE.trim()))){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }
}
