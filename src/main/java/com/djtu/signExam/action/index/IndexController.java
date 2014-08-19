package com.djtu.signExam.action.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 14-7-23.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value={"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/aboutUs")
    public String aboutUs(){
        return "aboutUs";
    }

    @RequestMapping("/noPermission")
    public String noPermission(){
        return "noPermission";
    }
}
