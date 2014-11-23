package com.djtu.signExam.service.link;

import java.util.List;

import com.djtu.signExam.model.TLink;

public interface LinkService {
	
	public List<TLink> getAllLink();
	
	public boolean deleteOneById(String id);
	
	public boolean doPriority(String id);
	
	public void addOne(TLink link);

}
