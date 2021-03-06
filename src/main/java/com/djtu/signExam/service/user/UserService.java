package com.djtu.signExam.service.user;

import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.model.TUserStudent;

import java.util.List;

/**
 * Created by root on 14-7-16.
 */
public interface UserService {

    //student
    public TUserStudent getStudentInfo(String id);

    public TUserStudent getStudentInfoByEmail(String email);

    public TUserAdmin getAdminInfo(String id);

    // adminer account manage

    public boolean addAdminer(TUserAdmin admin);

    public boolean removeAdminer(String id);

    public boolean updateAdminer(TUserAdmin admin);

    public boolean checkEmailExist(String email);

    public boolean resetAdminPassword(String userId,String pwd);

    public boolean resetStudentPassword(String userId,String pwd);

    //学院帐号 及 教务账号

    public List<TUserAdmin> getAllAccountByType(int userType);

    public boolean addAcer();

    public boolean removeAcer();

    public boolean updateAcer();


}
