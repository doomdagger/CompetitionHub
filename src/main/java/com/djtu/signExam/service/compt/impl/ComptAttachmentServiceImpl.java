package com.djtu.signExam.service.compt.impl;

import com.djtu.signExam.dao.impl.TComptAttachmentDao;
import com.djtu.signExam.dao.support.IOperators;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TComptAttachment;
import com.djtu.signExam.service.compt.ComptAttchmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by JOECHOW on 2014/8/27.
 */

@Component("comptAttachmentService")
public class ComptAttachmentServiceImpl implements ComptAttchmentService {

    @Autowired
    private TComptAttachmentDao tComptAttachmentDao;

    @Override
    public boolean addOne(TComptAttachment tComptAttachment) {
        Integer key = (Integer) tComptAttachmentDao.add(tComptAttachment);
        if(key != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOneById(String id) {
        return tComptAttachmentDao.deleteById(id);
    }

    /**
     *获取所有附件，返回List
     * @param id:主表ID
     * @return
     */
    @Override
    public List<TComptAttachment> getAllItemsBySkId(String id) {
        return tComptAttachmentDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_compt", id).orderBy(Sortable.inSort("createtime", IOperators.SORT.DESC)));
    }
}
