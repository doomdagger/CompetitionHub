package com.djtu.signExam.service.link.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.dao.impl.TLinkDao;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.dao.support.IOperators.SORT;
import com.djtu.signExam.model.TLink;
import com.djtu.signExam.service.link.LinkService;
import com.djtu.signExam.util.Util;


@Component("linkService")
public class LinkServiceImpl implements LinkService {

	@Autowired
	private TLinkDao tLinkDao;
	
	@Override
	public List<TLink> getAllLink() {
		return tLinkDao.findAllByWrapper(SQLWrapper.instance().selectAll().orderBy(Sortable.inSort("createtime", SORT.DESC)));
	}

	@Override
	public boolean deleteOneById(String id) {
		return tLinkDao.deleteById(id);
	}

	@Override
	public void addOne(TLink link) {
		tLinkDao.add(link);
	}

	@Override
	public boolean doPriority(String id) {
		return tLinkDao.updateByWrapper(SQLWrapper.instance().update().set("createtime", Util.dateToString((Date)new Date())).where().eq("ID", id));
	}

}
