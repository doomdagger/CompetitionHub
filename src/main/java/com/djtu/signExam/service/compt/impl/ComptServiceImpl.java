package com.djtu.signExam.service.compt.impl;

import com.djtu.signExam.dao.impl.TComptDao;
import com.djtu.signExam.dao.support.IOperators;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.service.compt.ComptService;
import com.djtu.signExam.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 14-7-24.
 */
@Component("comptService")
public class ComptServiceImpl implements ComptService {

    @Autowired
    public TComptDao tComptDao;

    @Override
    public List<TCompt> getComByPage(String userId,Pageable pageable) {
        SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("sk_t_userAdmin", userId).orderBy(Sortable.inSort("isTop", IOperators.SORT.DESC)).orderBy(Sortable.inSort("createtime", IOperators.SORT.DESC));
        //get pageCount
        pageable.setPageCount(tComptDao.getPageCount(pageable.getPageSize(), tComptDao.getCountByWrapper(sqlWrapper)));//no select in page
        return tComptDao.findAllByWrapper(sqlWrapper.limit(pageable));//select in page
    }

    @Override
    public List<TCompt> getAllComByPage(Pageable pageable) {
        SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().orderBy(Sortable.inSort("isTop", IOperators.SORT.DESC)).orderBy(Sortable.inSort("createtime", IOperators.SORT.DESC));
        //get pageCount
        pageable.setPageCount(tComptDao.getPageCount(pageable.getPageSize(), tComptDao.getCountByWrapper(sqlWrapper)));//no select in page
        return tComptDao.findAllByWrapper(sqlWrapper.limit(pageable));
    }
    
    @Override
	public List<TCompt> getAllIndexListByPage(Pageable pageable) {
    	SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().ge("status", 3).orderBy(Sortable.inSort("isTop", IOperators.SORT.DESC)).orderBy(Sortable.inSort("createtime", IOperators.SORT.DESC)).limit(pageable);
        return tComptDao.findAllByWrapper(sqlWrapper);
	}

    @Override
    public TCompt getComptById(String id) {
        return tComptDao.findOneById(id);
    }

    @Override
    public boolean updateCompt(TCompt tCompt) {
        return tComptDao.update(tCompt);
    }

    @Override
    public boolean deleteComptById(String id) {
        return tComptDao.deleteById(id);
    }

    @Override
    public Integer addCompt(TCompt tCompt) {
        return (Integer) tComptDao.add(tCompt);
    }

    @Override
    public boolean updateStatus(String id,int status) {
        SQLWrapper sqlWrapper = SQLWrapper.instance().update().set("status",status).where().eq("ID",id);
        return tComptDao.updateByWrapper(sqlWrapper);
    }

    @Override
    public int getPageCount(int pageSize) {
        return tComptDao.getPageCount(pageSize);
    }

    
    /*
     * 设置置顶
     * @see com.djtu.signExam.service.compt.ComptService#setToTop(java.lang.String, boolean)
     */
	@Override
	public boolean setToTop(String id, boolean isTop) {
		SQLWrapper sqlWrapper = SQLWrapper.instance();
		if(isTop){
			sqlWrapper.update().set("isTop", 1).set("createtime", Util.dateToString(new Date())).where().eq("ID", id);
		}else{
			sqlWrapper.update().set("isTop", 0).where().eq("ID", id);
		}
		return tComptDao.updateByWrapper(sqlWrapper);
	}

	
}
