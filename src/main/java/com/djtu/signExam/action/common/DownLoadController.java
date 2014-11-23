package com.djtu.signExam.action.common;

import com.djtu.signExam.util.DownLoadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JOECHOW on 2014/10/12 0012.
 */
@Controller
@RequestMapping("/download")
public class DownLoadController {

    @RequestMapping("/download")
    public void startDownload(HttpServletRequest request,HttpServletResponse response,@RequestParam String path){
        DownLoadUtil.dl(request,response,path);
    }
}
