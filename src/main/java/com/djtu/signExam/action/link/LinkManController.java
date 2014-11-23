package com.djtu.signExam.action.link;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djtu.signExam.model.TLink;
import com.djtu.signExam.service.link.LinkService;
import com.djtu.signExam.util.StringConst;


@Controller
@RequestMapping("/man/admin/link")
public class LinkManController {
	
	@Autowired
	private LinkService linkService;
	
	private static final String NAV = "navbar";
	private static final String LINK = "link";
	
	@RequestMapping("/list")
	public String list(Model model){
		List<TLink> linkList = linkService.getAllLink();
		model.addAttribute("linkList", linkList);
		model.addAttribute(NAV, LINK);
		return "adminFriendLink";
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String add(@RequestParam String title,@RequestParam String hrefLink){
		TLink link = new TLink();
		link.setCreatetime(new Date());
		link.setTitle(title);
		link.setHref(hrefLink);
		linkService.addOne(link);
		return "redirect:/man/admin/link/list";
	}
	
	@RequestMapping("/delete")
	public @ResponseBody String delete(@RequestParam String linkId){
		return linkService.deleteOneById(linkId)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
	}
	
	@RequestMapping("/doPriority")
	public @ResponseBody String doPriority(@RequestParam String linkId){
		return linkService.doPriority(linkId)?StringConst.AJAX_SUCCESS:StringConst.AJAX_FAIL;
	}

}
