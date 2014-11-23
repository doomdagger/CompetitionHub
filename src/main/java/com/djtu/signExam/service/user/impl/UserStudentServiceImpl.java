package com.djtu.signExam.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.dao.impl.TUserStudentDao;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.UserStudentService;


@Component("userStudentService")
public class UserStudentServiceImpl implements UserStudentService {

	@Autowired
	private TUserStudentDao studentDao;
	@Override
	public List<TUserStudent> getAllStudentListInPage(Pageable pageable) {
		SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll();
		pageable.setPageCount(studentDao.getPageCount(pageable.getPageSize(), studentDao.getCountByWrapper(sqlWrapper)));
		return studentDao.findAllByWrapper(sqlWrapper.limit(pageable));
	}
	
}
