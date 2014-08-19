package com.djtu.signExam.action.compt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 14-7-24.
 */
@Controller
@RequestMapping("/compt")
public class ComptController {

    @RequestMapping("/list")
    public String list(){
        return "comptList";
    }
}
