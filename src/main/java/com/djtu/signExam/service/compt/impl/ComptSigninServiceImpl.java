package com.djtu.signExam.service.compt.impl;

import com.djtu.signExam.dao.impl.TComptAttachmentDao;
import com.djtu.signExam.dao.impl.TSigninDao;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.model.TComptAttachment;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.service.compt.ComptSigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by JOECHOW on 2014/9/4.
 */
@Component("comptSigninService")
public class ComptSigninServiceImpl implements ComptSigninService {

    @Autowired
    private TSigninDao signinDao;
    @Autowired
    private TComptAttachmentDao tComptAttachmentDao;

    @Override
    public boolean addTeamMember(TSignin model) {
        Object key = signinDao.add(model);
        if(key != null && key != ""){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTeamMember(String id) {
        return signinDao.deleteById(id);
    }

    @Override
    public boolean updateInfo(TSignin model) {
        return signinDao.update(model);
    }

    @Override
    public boolean addAttachMent(TComptAttachment model) {
        Object key = tComptAttachmentDao.add(model);
        if(key != null && key != ""){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkSignInExist(String comptId, String stuId) {
        TSignin model = signinDao.findOneByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_compt",comptId).eq("sk_t_student",stuId));
        if(model != null){
            return true;
        }
        return false;
    }

    @Override
    public List<TSignin> getCompetitionByUserId(String id) {
        return signinDao.findAllByWrapper(SQLWrapper.instance().selectAll().eq("sk_t_student",id).eq("isDelete",0));
    }

    //直接根据signIn的ID逻辑删除
    @Override
    public boolean quitFromCompetitionByUserId(String sid) {
        return signinDao.deleteById(sid);
    }
}
