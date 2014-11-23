package com.djtu.signExam.service.calendar;

import java.util.List;

import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TComptCalendar;

public interface CalendarService {
	
	public List<TComptCalendar> getAllListInPage(Pageable pageable);
	
	public TComptCalendar getOneById(String id);
	
	public void addOne(TComptCalendar calendar);
	
	public boolean updateOne(TComptCalendar calendar);
	
	public boolean deleteOneById(String id);
	
	public boolean priorityOneById(String id);
	
	

}
