package com.djtu.signExam.action.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djtu.signExam.model.TComptCalendar;
import com.djtu.signExam.service.calendar.CalendarService;


@Controller
@RequestMapping("/calendar")
public class CalendarController {
	
	@Autowired
	private CalendarService calService;
	
	@RequestMapping("/detail/{calId}")
	public String detail(@PathVariable String calId,Model model){
		//get the calendar list
		TComptCalendar calendar = calService.getOneById(calId);
		
		model.addAttribute("calendar", calendar);
		return "calendarDetail";
	}
}
