package com.djtu.signExam.service.compt.impl;

import com.djtu.signExam.dao.impl.TComptDao;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.model.TCompt;
import com.djtu.signExam.service.compt.ComptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by root on 14-7-24.
 */
@Component("comptService")
public class ComptServiceImpl implements ComptService {

    @Autowired
    public TComptDao tComptDao;

    @Override
    public List<TCompt> getComByPage(Pageable pageable) {
        SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().limit(pageable);
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
}
