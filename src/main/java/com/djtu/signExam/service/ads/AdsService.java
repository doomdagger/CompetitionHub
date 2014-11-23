package com.djtu.signExam.service.ads;

import java.util.List;

import com.djtu.signExam.model.TAds;

public interface AdsService {
	
	public List<TAds> getAllAds();
	
	public boolean deleteOneAdById(String id);
	
	public boolean doPriority(String id);
	
	public void addOneAd(TAds ads);
}
