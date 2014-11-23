package com.djtu.signExam.service.compt;

import com.djtu.signExam.model.TResultProf;

public interface ProfService {
	
	public void addOne(TResultProf prof);
	
	public TResultProf getOneByComptId(String comptId);
}
