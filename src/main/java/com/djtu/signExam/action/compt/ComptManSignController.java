package com.djtu.signExam.action.compt;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.service.compt.ComptManSignService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 控制报名审核的 controller
 * Created by JOECHOW on 2014/10/13 0013.
 */
@Controller
@RequestMapping("/man/compt/sign")
public class ComptManSignController {

    @Autowired
    private ComptManSignService comptManSignService;

    private static final String NAV = "navbar";
    private static final String COM = "comptMan";

    /**
     * 显示选中赛事的报名概况一览
     * @return
     */
    @RequestMapping("/checkSignList/{comptId}")
    public String checkSignList(Model model,@PathVariable("comptId") String comptId){
        /*Pageable pageable = Pageable.inPage(currpage,ProjectPageConfig.MAN_SIGN_LIST_PAGESIZE);
        System.out.println("compt: "+comptId);
        System.out.println("currpage: "+currpage);
        List<TSignin> signList = comptManSignService.getSignLeaderListInPage(comptId,false,false,pageable);

        System.out.println(pageable.getCurrentPage()+" "+pageable.getPageSize()+" "+pageable.getPageCount());
        model.addAttribute("pageable",pageable);
        model.addAttribute("signList",signList);*/
        model.addAttribute("comptLink",comptId);
        model.addAttribute("currPage",1);
        model.addAttribute(NAV,COM);
        return "comptManSignList";
    }

    @RequestMapping("/teamDetail/{teamNo}")
    public String teamDetail(HttpServletRequest request,Model model,@PathVariable String teamNo){
        model.addAttribute(NAV,COM);
        return "comptManSignTeamDetail";
    }

    /**
     *
     * @param comptId:赛事ID，用于查找报名列表
     * @param filter:true为查看全部
     * @param order;true为顺序时间排列
     * @param currPage:当前页
     * @return
     */
    @RequestMapping(value = "/getSignList")
    public @ResponseBody String getSignList(@RequestParam String comptId,@RequestParam Boolean filter,@RequestParam Boolean order,@RequestParam Integer currPage){
        Pageable pageable = Pageable.inPage(currPage, ProjectPageConfig.MAN_SIGN_LIST_PAGESIZE);
        List<TSignin> signList = comptManSignService.getSignLeaderListInPage(comptId, filter, order, pageable);
        JSONObject result = new JSONObject();
        result.put("isSuccess",true);
        result.put("signList",signList);
        result.put("page",new JSONObject(pageable));
        return result.toString();
    }
}
