package com.djtu.signExam.service.compt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.dao.impl.TResultProfDao;
import com.djtu.signExam.model.TResultProf;
import com.djtu.signExam.service.compt.ProfService;


@Component("profService")
public class ProfServiceImpl implements ProfService{

	@Autowired
	private TResultProfDao profDao;
	
	@Override
	public void addOne(TResultProf prof) {
		profDao.add(prof);
	}

	@Override
	public TResultProf getOneByComptId(String comptId) {
		return profDao.findOneById(comptId);
	}
	
}
