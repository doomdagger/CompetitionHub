package com.djtu.signExam.action.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by JOECHOW on 2014/8/21.
 */

@Controller
@RequestMapping("/man")
public class ManController {

    @RequestMapping("/")
    public String index(){
        //should judge the user type here and return the correct page and data
        return "userAcademy";
    }
}
