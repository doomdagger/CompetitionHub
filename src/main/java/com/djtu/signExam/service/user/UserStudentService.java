package com.djtu.signExam.service.user;

import java.util.List;

import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TUserStudent;

public interface UserStudentService {
	
	public List<TUserStudent> getAllStudentListInPage(Pageable pageable);

}
