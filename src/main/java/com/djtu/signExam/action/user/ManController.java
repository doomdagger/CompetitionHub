package com.djtu.signExam.action.user;

import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by JOECHOW on 2014/8/21.
 */

@Controller
@RequestMapping("/man")
public class ManController {

    @Autowired
    private UserService userService;

    //when get here shoud be access the "ManControllerInterceptor"
    @RequestMapping(value = {"","/","/index"})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        //should judge the user type here and return the correct page and data
        HashMap<String,Object> user = (HashMap<String, Object>) request.getSession().getAttribute(SessionConst.U_USER);
        Integer userId = user.get(SessionConst.U_USER_LINK)!=null?(Integer) user.get(SessionConst.U_USER_LINK):0;
        TUserAdmin admin = userService.getAdminInfo(userId.toString());
        return "userAdmin";
    }
}
