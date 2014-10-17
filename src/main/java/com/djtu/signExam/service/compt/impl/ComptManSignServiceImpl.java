package com.djtu.signExam.service.compt.impl;

import com.djtu.signExam.dao.impl.TSigninDao;
import com.djtu.signExam.dao.support.IOperators;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TSignin;
import com.djtu.signExam.service.compt.ComptManSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by JOECHOW on 2014/10/15 0015.
 */
@Component("comptManSignService")
public class ComptManSignServiceImpl implements ComptManSignService {

    @Autowired
    private TSigninDao signDao;
    @Override
    public List<TSignin> getSignLeaderListInPage(String comptId,boolean filter, boolean timeOrder, Pageable pageable) {
        SQLWrapper sqlWrapper = null;
        if(filter){
            //如果filter为true，则查看已通过
            sqlWrapper = SQLWrapper.instance().selectAll().where().eq("sk_t_compt",comptId).eq("isLeader", 1).eq("isPass",1).orderBy(Sortable.inSort("createtime", timeOrder? IOperators.SORT.ASC:IOperators.SORT.DESC));
        }else{
            //如果filter为false，则查看全部
            sqlWrapper = SQLWrapper.instance().selectAll().where().eq("sk_t_compt",comptId).eq("isLeader",1).orderBy(Sortable.inSort("isPass",IOperators.SORT.DESC)).orderBy(Sortable.inSort("createtime", timeOrder? IOperators.SORT.ASC:IOperators.SORT.DESC));
        }
        //set pageCount
        pageable.setPageCount(signDao.getPageCount(pageable.getPageSize(), signDao.getCountByWrapper(sqlWrapper)));
        //get resultSet
        return signDao.findAllByWrapper(sqlWrapper.limit(pageable));
    }

    @Override
    public List<TSignin> getSignTeamMemberList(String teamNo) {
        //teamNo is unique
        return signDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("teamNo", teamNo));
    }
}
