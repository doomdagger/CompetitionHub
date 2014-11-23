package com.djtu.signExam.service.ads.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.dao.impl.TAdsDao;
import com.djtu.signExam.dao.support.IOperators.SORT;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TAds;
import com.djtu.signExam.service.ads.AdsService;
import com.djtu.signExam.util.Util;

@Component("adsService")
public class AdsServiceImpl implements AdsService {

	@Autowired
	private TAdsDao tAdsDao;
	
	@Override
	public List<TAds> getAllAds() {
		return tAdsDao.findAllByWrapper(SQLWrapper.instance().selectAll().orderBy(Sortable.inSort("createtime", SORT.DESC)));
	}

	@Override
	public boolean deleteOneAdById(String id) {
		return tAdsDao.deleteById(id);
	}
	
	@Override
	public void addOneAd(TAds ads) {
		tAdsDao.add(ads);
	}

	@Override
	public boolean doPriority(String id) {
		return tAdsDao.updateByWrapper(SQLWrapper.instance().update().set("createtime", Util.dateToString((Date)new Date())).where().eq("ID", id));
	}

}
