package com.djtu.signExam.action.user;

import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.StringConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by JOECHOW on 2014/9/10.
 */
@Controller
@RequestMapping("/man/admin")
public class AdminAccountController {

    @Autowired
    private UserService userService;

    @RequestMapping("/accountGet")
    public String accountGet(Model model){
        List<TUserAdmin> userList = userService.getAllAccountByType(StringConst.ADMIN_ACCOUNT);
        model.addAttribute("userList",userList);
        return "adminAccount";//管理员账号
    }

    @RequestMapping("/academyAccount")
    public String academyAccountGet(Model model){
        List<TUserAdmin> userList = userService.getAllAccountByType(StringConst.ACADE_ACCOUNT);
        model.addAttribute("userList",userList);
        return "adminAcademyAccount";//学院账号
    }

    @RequestMapping("/djtuAccount")
    public String djtuAccountGet(Model model){
        List<TUserAdmin> userList = userService.getAllAccountByType(StringConst.DJTU_ACCOUNT);
        model.addAttribute("userList",userList);
        return "adminDjtuAccount";//教务账号
    }

    @RequestMapping("/addAccount")
    public @ResponseBody String addAccount(@RequestParam String name,@RequestParam String cellphone,@RequestParam String pwd,@RequestParam String email){
        TUserAdmin admin = new TUserAdmin();
        if(userService.addAdminer(admin)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }

    @RequestMapping("/deleteAccount")
    public @ResponseBody String deleteAccount(@RequestParam String link){
        if(userService.removeAdminer(link)){
            return StringConst.AJAX_SUCCESS;
        }
        return StringConst.AJAX_FAIL;
    }
}
