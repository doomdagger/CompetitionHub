package com.djtu.signExam.service.compt.impl;

import com.djtu.signExam.dao.impl.TComptDao;
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
    @Autowired
    private TComptDao comptDao;
    
    @Override
    public List<TSignin> getSignLeaderListInPage(String comptId,boolean filter, boolean timeOrder, Pageable pageable) {
        SQLWrapper sqlWrapper = null;
        if(filter){
            //如果filter为true，则查看已通过
            sqlWrapper = SQLWrapper.instance().selectAll().where()
            		.eq("sk_t_compt",comptId)
            		.eq("isLeader", 1)
            		.eq("isPass",1)
            		.orderBy(Sortable.inSort("orderNum", IOperators.SORT.ASC))
            		.orderBy(Sortable.inSort("createtime", timeOrder? IOperators.SORT.ASC:IOperators.SORT.DESC));
        }else{
            //如果filter为false，则查看全部
            sqlWrapper = SQLWrapper.instance().selectAll().where()
            		.eq("sk_t_compt",comptId)
            		.eq("isLeader",1)
            		.orderBy(Sortable.inSort("isPass", IOperators.SORT.DESC))
            		.orderBy(Sortable.inSort("orderNum",IOperators.SORT.ASC))
            		.orderBy(Sortable.inSort("createtime", timeOrder? IOperators.SORT.ASC:IOperators.SORT.DESC));
        }
        //set pageCount
        pageable.setPageCount(signDao.getPageCount(pageable.getPageSize(), signDao.getCountByWrapper(sqlWrapper)));
        //get resultSet
        return signDao.findAllByWrapper(sqlWrapper.limit(pageable));
    }
    
    /*
     * (non-Javadoc)选择已经通过审核的队伍
     * @see com.djtu.signExam.service.compt.ComptManSignService#getAllPassTeamByComptId(java.lang.String)
     */
    @Override
	public List<TSignin> getAllPassTeamByComptId(String comptId) {
		return signDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_compt", comptId).eq("isPass", 1));
	}

    @Override
    public List<TSignin> getSignTeamMemberList(String teamNo) {
        //teamNo is unique
        return signDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("teamNo", teamNo).ne("isLeader", 1));
    }
    
    @Override
	public TSignin getTeamLeader(String teamNo) {
		return signDao.findOneByWrapper(SQLWrapper.instance().selectAll().where().eq("teamNo", teamNo).eq("isLeader", 1));
	}
    
    /*
     * 根据TSignin的ID更新TSignin的OrderNum
     * @see com.djtu.signExam.service.compt.ComptAttchmentService#updateOrderNum(java.lang.String, int)
     */
	@Override
	public boolean updateOrderNum(String signLink, int orderNum) {
		return signDao.updateByWrapper(SQLWrapper.instance().update().set("orderNum", orderNum).where().eq("ID", signLink));
	}

	@Override
	public boolean updateSignPriority(String signLink, boolean isPriority) {
		SQLWrapper sqlWrapper = SQLWrapper.instance();
		if(isPriority){
			sqlWrapper.update().set("isPriority", 1).where().eq("ID",signLink);
			return signDao.updateByWrapper(sqlWrapper)?true:false;
		}else{
			sqlWrapper.update().set("isPriority", 0).where().eq("ID",signLink);
			return signDao.updateByWrapper(sqlWrapper)?true:false;
		}
	}

	@Override
	public boolean updateSignPass(String signLink, boolean isPass) {
		SQLWrapper sqlWrapper = SQLWrapper.instance();
		if(isPass){
			sqlWrapper.update().set("isPass",1).where().eq("ID", signLink);
		}else{
			sqlWrapper.update().set("isPass",0).where().eq("ID", signLink);
		}
		return signDao.updateByWrapper(sqlWrapper)?true:false;
	}

	@Override
	public boolean updateMultiSignPriority(List<String> signList) {
		SQLWrapper sqlWrapper = SQLWrapper.instance().update().set("isPriority",1).where().eq("ID",signList.get(0));
		for(int i = 1,size=signList.size(); i<size; i++){
			sqlWrapper.or(SQLWrapper.instance().eq("ID", signList.get(i)));
		}
		return signDao.updateByWrapper(sqlWrapper)?true:false;
	}

	@Override
	public boolean updateMultiSignPass(List<String> signList) {
		SQLWrapper sqlWrapper = SQLWrapper.instance().update().set("isPass",1).where().eq("ID",signList.get(0));
		for(int i = 1,size=signList.size(); i<size; i++){
			sqlWrapper.or(SQLWrapper.instance().eq("ID", signList.get(i)));
		}
		return signDao.updateByWrapper(sqlWrapper)?true:false;
	}

	@Override
	public List<TSignin> getAllSignByComptId(String comptId) {
		return signDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_compt",comptId));
	}

	@Override
	public boolean addReward(String teamNo,String result) {
		return signDao.updateByWrapper(SQLWrapper.instance().update().set("isReward",1).set("reward", result).where().eq("teamNo",teamNo).eq("isPass", 1));
	}

	/*
	 * (根据赛事ID，确认该赛事有人获奖,设置isReward状态为1)
	 * @see com.djtu.signExam.service.compt.ComptManSignService#confirmReward(java.lang.String)
	 */
	@Override
	public boolean confirmReward(String comptId,boolean isReward) {
		return comptDao.updateByWrapper(SQLWrapper.instance().update().set("isReward", isReward?1:0).where().eq("ID", comptId));
	}

	@Override
	public boolean confirmFinish(String comptId,int status) {
		return comptDao.updateByWrapper(SQLWrapper.instance().update().set("status", status).where().eq("ID", comptId));
	}

	@Override
	public List<TSignin> getAllRewardTeam(String comptId) {
		return signDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_compt", comptId).eq("isPass",1).eq("isReward",1));
	}

	

	
}
