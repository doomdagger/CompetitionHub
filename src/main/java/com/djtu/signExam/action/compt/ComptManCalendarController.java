package com.djtu.signExam.action.compt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/man/admin/comptCalendar")
public class ComptManCalendarController {
	
	private static final String NAV = "navbar";
	private static final String CAL = "calendar";
	
	
	@RequestMapping(value = "/list")
	public String list(Model model){
		model.addAttribute(NAV, CAL);
		return "";
	}
	
	@RequestMapping(value = "/add")
	public String add(){
		return "";
	}
	
	@RequestMapping(value = "/delete")
	public @ResponseBody String delete(){
		return "";
	}

}
