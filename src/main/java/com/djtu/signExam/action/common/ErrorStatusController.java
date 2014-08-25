package com.djtu.signExam.action.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by JOECHOW on 2014/8/21.
 */

@Controller
@RequestMapping("/status")
public class ErrorStatusController {

    @RequestMapping("/{code}")
    public String dealStatusCode(@PathVariable int code,Model model){
        if(code==404){
            return "errorpage";
        }
        model.addAttribute("message","Status: "+code);
        return "errorpage";
    }
}