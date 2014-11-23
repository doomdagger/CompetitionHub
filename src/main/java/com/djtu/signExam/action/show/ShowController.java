package com.djtu.signExam.action.show;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TShow;
import com.djtu.signExam.model.TShowAlbum;
import com.djtu.signExam.service.show.ShowService;

/**
 * Created by JOECHOW on 2014/8/28.
 */

@Controller
@RequestMapping("/show")
public class ShowController {
	
	
	@Autowired
	private ShowService showService;
	

    @RequestMapping(value = {"/","list"})
    public String list(Model model){
        return "redirect:/show/list/1";
    }

    /*
     * 风采展示页面显示
     */
    @RequestMapping(value = "/list/{currpage}")
    public String listInPage(@PathVariable Integer currpage,Model model){
    	Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.SHOW_INDEX_LIST_NUM);
    	List<TShow> showList = showService.getAllShowInPage(pageable);
    	//add to model attribute
    	model.addAttribute("pageable", pageable);
    	model.addAttribute("showList", showList);
        return "showList";
    }
    
    /*
     * 根据showId显示该show下面的所有照片
     */
    @RequestMapping("/album/{showLink}")
    public String album(@PathVariable String showLink,Model model){
    	//get picLIst
    	List<TShowAlbum> picList = showService.getAllPicByShowId(showLink);
    	//get show
    	TShow show = showService.getShowById(showLink);
    	
    	model.addAttribute("show", show);
    	model.addAttribute("picList", picList);
    	return "showAlbum";
    }
}
