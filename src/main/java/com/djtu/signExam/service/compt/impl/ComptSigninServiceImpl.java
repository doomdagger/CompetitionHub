package com.djtu.signExam.service.compt.impl;

import com.djtu.signExam.dao.impl.TComptAttachmentDao;
import com.djtu.signExam.dao.impl.TSigninDao;
import com.djtu.signExam.dao.support.IOperators;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TComptAttachment;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.service.compt.ComptSigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.Sort;

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
    public TSignin getSignInById(String id) {
        return signinDao.findOneById(id);
    }

    @Override
    public TSignin getSignInByComIdByStuId(String comptId, String stuId) {
        //根据赛事ID 和 用户ID可以确定一个 sign
        return signinDao.findOneByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_compt",comptId).eq("sk_t_student",stuId));
    }

    /**
     *
     * @param teamNo：每个队伍的唯一标示
     * @param comptId：赛事ID
     * @return：根据组长和时间顺序排序
     */
    @Override
    public List<TSignin> getAllSignIn(String teamNo, Integer comptId) {
        List<TSignin> list = signinDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("teamNo", teamNo).eq("sk_t_compt", comptId).orderBy(Sortable.inSort("isLeader", IOperators.SORT.DESC)).orderBy(Sortable.inSort("createtime", IOperators.SORT.ASC)));
        return list;
    }

    /**
     *
     * @param id：TSign的ID
     * @param stuId：学生ID
     * @return：如果存在返回true，不存在返回false
     */
    @Override
    public boolean checkSignInExistByIdByStuId(String id, String stuId) {
        TSignin signin = signinDao.findOneByWrapper(SQLWrapper.instance().selectAll().where().eq("ID",id).eq("sk_t_student",stuId));
        if(signin!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean addTeamMember(TSignin model) {
        Object key = signinDao.add(model);
        if(!"".equals(key)){
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
    public boolean deleteAttachMent(String id) {
        return tComptAttachmentDao.deleteById(id);
    }

    @Override
    public List<TComptAttachment> getAllAttachment(String sk_t_compt, String teamNo) {
        return tComptAttachmentDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_compt", sk_t_compt).eq("teamNo", teamNo).orderBy(Sortable.inSort("createtime", IOperators.SORT.DESC)));
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
    public List<TSignin> getCompetitionByUserIdInPage(String id,Pageable pageable) {
        SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("sk_t_student", id).eq("isDelete", 0).orderBy(Sortable.inSort("createtime", IOperators.SORT.DESC));
        pageable.setPageCount(signinDao.getPageCount(pageable.getPageSize(),signinDao.getCountByWrapper(sqlWrapper)));
        return signinDao.findAllByWrapper(sqlWrapper.limit(pageable));
    }

    @Override
    public List<TSignin> getCompetitionByUserEmailInPage(String email, Pageable pageable) {
        SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("email", email).eq("isDelete", 0).orderBy(Sortable.inSort("createtime", IOperators.SORT.DESC));
        pageable.setPageCount(signinDao.getPageCount(pageable.getPageSize(),signinDao.getCountByWrapper(sqlWrapper)));
        return signinDao.findAllByWrapper(sqlWrapper.limit(pageable));
    }

    //直接根据signIn的ID物理删除
    @Override
    public boolean quitFromCompetitionByUserId(String signId) {
        return signinDao.deleteById(signId);
    }

    @Override
    public boolean quitFromCompetitionByTeamNo(String teamNo) {
        return signinDao.deleteByWrapper(SQLWrapper.instance().delete().where().eq("teamNo",teamNo));
    }

    @Override
    public int getPageCount(int pageSize) {
        return signinDao.getPageCount(pageSize);
    }

    @Override
    public int getPageCount(int pageSize, String id) {
        long count = signinDao.getCountByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_student",id));
        return signinDao.getPageCount(pageSize,count);
    }

    @Override
    public boolean isExistAnEmailInTeamNo(String teamNo, String email) {
        TSignin signin = signinDao.findOneByWrapper(SQLWrapper.instance().selectAll().where().eq("teamNo",teamNo).eq("email",email));
        return signin!=null?true:false;
    }
}
