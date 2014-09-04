package com.djtu.signExam.service.compt;

import com.djtu.signExam.model.TComptAttachment;
import com.djtu.signExam.model.TSignin;

import java.util.List;

/**
 * Created by root on 14-7-24.
 */
public interface ComptSigninService {

    public boolean addTeamMember(TSignin model);

    public boolean deleteTeamMember(String id);

    public boolean updateInfo(TSignin model);

    public boolean addAttachMent(TComptAttachment model);

    public boolean checkSignInExist(String comptId,String stuId);

    public List<TSignin> getCompetitionByUserId(String id);

    public boolean quitFromCompetitionByUserId(String sid);


}
