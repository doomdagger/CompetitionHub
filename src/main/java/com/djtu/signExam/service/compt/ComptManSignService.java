package com.djtu.signExam.service.compt;

import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TSignin;

import java.util.List;

/**
 * Created by JOECHOW on 2014/10/15 0015.
 */
public interface ComptManSignService {

    public List<TSignin> getSignLeaderListInPage(String comptId,boolean filter,boolean timeOrder,Pageable pageable);

    public List<TSignin> getSignTeamMemberList(String teamNo);

}
