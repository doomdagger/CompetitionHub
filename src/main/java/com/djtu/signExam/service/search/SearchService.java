package com.djtu.signExam.service.search;

import java.util.List;

import com.djtu.signExam.model.TSignin;

public interface SearchService {
	
	public List<TSignin> doSearchForRewardSignByEmail(String email);
	
	public List<TSignin> doSearchForRewardSignByName(String name);

}
