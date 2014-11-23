package com.djtu.signExam.service.search.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.dao.impl.TSigninDao;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.service.search.SearchService;


@Component("searchService")
public class SearchServiceImpl implements SearchService {

	@Autowired
	private TSigninDao signDao;
	
	
	/*
	 * 根据Email查找
	 * @see com.djtu.signExam.service.search.SearchService#doSearchForRewardSignByEmail(java.lang.String)
	 */
	@Override
	public List<TSignin> doSearchForRewardSignByEmail(String email) {
		SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("email", email).eq("isReward", 1);
		return signDao.findAllByWrapper(sqlWrapper);
	}
	
	
	/*
	 * 根据名字来查找
	 * @see com.djtu.signExam.service.search.SearchService#doSearchForRewardSignByName(java.lang.String)
	 */
	@Override
	public List<TSignin> doSearchForRewardSignByName(String name) {
		SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("name", name).eq("isReward", 1);;
		return signDao.findAllByWrapper(sqlWrapper);
	}
	
}
