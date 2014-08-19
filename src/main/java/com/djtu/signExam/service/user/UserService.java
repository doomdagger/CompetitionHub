package com.djtu.signExam.service.user;

import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.model.TUserStudent;

/**
 * Created by root on 14-7-16.
 */
public interface UserService {

    public TUserStudent getStudentInfo();

    public TUserAdmin getAdminInfo();

    // adminer account manage

    public boolean addAdminer();

    public boolean removeAdminer();

    public boolean updateAdminer();


    //学院帐号 及 教务账号
    public boolean addAcer();

    public boolean removeAcer();

    public boolean updateAcer();


}
