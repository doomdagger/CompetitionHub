package com.djtu.signExam.action.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djtu.signExam.config.ProjectPageConfig;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.UserStudentService;

@Controller
@RequestMapping("/man/admin/student/")
public class AdminStudentController {
	
	private static final String NAV = "navbar";
	private static final String STU = "student";
	
	@Autowired
	private UserStudentService userStudentService;
	
	
	@RequestMapping(value = "/list")
	public String list(){
		return "redirect:/man/admin/student/list/1";//默认显示第一页
	}
	
	@RequestMapping(value = "/list/{currpage}")
	public String listInPage(@PathVariable Integer currpage,Model model){
		Pageable pageable = Pageable.inPage(currpage, ProjectPageConfig.MAN_STUD_LIST_PAGESIZE);
		List<TUserStudent> userList = userStudentService.getAllStudentListInPage(pageable);
		model.addAttribute("pageable", pageable);
		model.addAttribute("userList", userList);
		model.addAttribute(NAV, STU);
		return "adminStudentList";
	}
	
}
