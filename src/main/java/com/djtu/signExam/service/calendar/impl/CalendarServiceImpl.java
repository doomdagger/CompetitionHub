package com.djtu.signExam.service.calendar.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.dao.impl.TComptCalendarDao;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.dao.support.IOperators.SORT;
import com.djtu.signExam.model.TComptCalendar;
import com.djtu.signExam.service.calendar.CalendarService;
import com.djtu.signExam.util.Util;


@Component("calendarService")
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private TComptCalendarDao calDao;
	
	@Override
	public List<TComptCalendar> getAllListInPage(Pageable pageable) {
		SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().orderBy(Sortable.inSort("createtime", SORT.DESC));
		//set the pagecount
		pageable.setPageCount(calDao.getPageCount(pageable.getPageSize(), calDao.getCountByWrapper(sqlWrapper)));
		//get the result list
		return calDao.findAllByWrapper(sqlWrapper.limit(pageable));
	}

	@Override
	public TComptCalendar getOneById(String id) {
		return calDao.findOneById(id);
	}

	@Override
	public void addOne(TComptCalendar calendar) {
		calDao.add(calendar);
	}

	@Override
	public boolean updateOne(TComptCalendar calendar) {
		return calDao.update(calendar);
	}

	@Override
	public boolean deleteOneById(String id) {
		return calDao.deleteById(id);
	}

	/*
	 * 根据ID优先一个记录，更新createtime
	 * @see com.djtu.signExam.service.calendar.CalendarService#priorityOneById(java.lang.String)
	 */
	@Override
	public boolean priorityOneById(String id) {
		return calDao.updateByWrapper(SQLWrapper.instance().update().set("createtime", Util.dateToString(new Date())).where().eq("ID", id));
	}

}
