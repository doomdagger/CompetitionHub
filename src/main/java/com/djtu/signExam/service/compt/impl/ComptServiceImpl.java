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
    public boolean addCompt(TCompt tCompt) {
        Object key = tComptDao.add(tCompt);
        if(key == "" || key == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStatus(int status) {
        SQLWrapper sqlWrapper = SQLWrapper.instance().update().set("status",status);
        return tComptDao.updateByWrapper(sqlWrapper);
    }
}
