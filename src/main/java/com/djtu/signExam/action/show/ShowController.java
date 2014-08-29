package com.djtu.signExam.action.show;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by JOECHOW on 2014/8/28.
 */

@Controller
@RequestMapping("/show")
public class ShowController {

    @RequestMapping(value = {"/","list"})
    public String list(Model model){
        return "showList";
    }

    @RequestMapping(value = "/list/{currpage}")
    public String listInPage(@PathVariable String currpage,Model model){
        return "showList";
    }
}
