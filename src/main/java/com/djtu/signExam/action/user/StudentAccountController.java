package com.djtu.signExam.action.user;

import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.UserService;
import com.djtu.signExam.util.DaoUtil;
import com.djtu.signExam.util.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = {"","/","/index"})
    public String index(HttpServletRequest request,HttpServletResponse response,Model model){
        HashMap<String,Object> user = (HashMap<String, Object>) request.getSession().getAttribute(SessionConst.U_USER);
        Integer userId = user.get(SessionConst.U_USER_LINK)!=null?(Integer) user.get(SessionConst.U_USER_LINK):0;
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
    public String myCompetition(){
        return "stuMyCompetition";
    }
}
