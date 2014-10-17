package com.djtu.signExam.service.compt;

import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TComptAttachment;
import com.djtu.signExam.model.TSignin;

import java.util.List;

/**
 * Created by root on 14-7-24.
 */
public interface ComptSigninService {

    public TSignin getSignInById(String id);

    public TSignin getSignInByComIdByStuId(String comptId,String stuId);

    public List<TSignin> getAllSignIn(String teamNo,Integer comptId);

    public boolean checkSignInExistByIdByStuId(String id,String stuId);

    public boolean addTeamMember(TSignin model);

    public boolean deleteTeamMember(String id);

    public boolean updateInfo(TSignin model);

    public boolean addAttachMent(TComptAttachment model);

    public boolean deleteAttachMent(String id);

    public List<TComptAttachment> getAllAttachment(String sk_t_compt,String teamNo);

    public boolean checkSignInExist(String comptId,String stuId);

    public List<TSignin> getCompetitionByUserIdInPage(String id,Pageable pageable);

    public List<TSignin> getCompetitionByUserEmailInPage(String email,Pageable pageable);

    public boolean quitFromCompetitionByUserId(String sid);

    public boolean quitFromCompetitionByTeamNo(String teamNo);

    public int getPageCount(int pageSize);

    public int getPageCount(int pageSize,String id);

    //extra
    public boolean isExistAnEmailInTeamNo(String teamNo,String email);


}
