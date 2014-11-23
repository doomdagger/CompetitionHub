package com.djtu.signExam.service.compt;

import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TSignin;

import java.util.List;

/**
 * Created by JOECHOW on 2014/10/15 0015.
 */
public interface ComptManSignService {

    public List<TSignin> getSignLeaderListInPage(String comptId,boolean filter,boolean timeOrder,Pageable pageable);
    
    public List<TSignin> getAllPassTeamByComptId(String comptId);

    public List<TSignin> getSignTeamMemberList(String teamNo);
    
    public TSignin getTeamLeader(String teamNo);
    
    public boolean updateOrderNum(String signLink,int orderNum);
    
    public boolean updateSignPriority(String signLink,boolean isPriority);
    
    public boolean updateSignPass(String signLink,boolean isPass);
    
    public boolean updateMultiSignPriority(List<String> signList);
    
    public boolean updateMultiSignPass(List<String> signList);
    
    
    public boolean confirmReward(String comptId,boolean isReward);
    
    public boolean addReward(String teamNo,String result);
    
    public boolean confirmFinish(String comptId,int status);
    
    public List<TSignin> getAllRewardTeam(String comptId);
    
    //use for export excel
    public List<TSignin> getAllSignByComptId(String comptId);

}
