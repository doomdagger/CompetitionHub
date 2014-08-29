package com.djtu.signExam.service.user.impl;

import com.djtu.signExam.dao.impl.TUserAdminDao;
import com.djtu.signExam.dao.impl.TUserStudentDao;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.RegisterAndLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by root on 14-7-16.
 */
@Component("registerAndLoginService")
public class RegisterAndLoginServiceImpl implements RegisterAndLoginService {

    @Autowired
    public TUserStudentDao tUserStudentDao;
    @Autowired
    public TUserAdminDao tUserAdminDao;

    @Override
    public boolean register(TUserStudent tUserStudent) {
        if(tUserStudentDao.add(tUserStudent) != null){
            return true;
        }
        return false;
    }

    @Override
    public void sendConfirmEmail(String email,String code) {
        //只对学生帐号
        if(email != null && code != null){
            SQLWrapper sqlWrapper = SQLWrapper.instance().update().set("a_isActive", "1").where().eq("a_email", email).eq("a_activateCode", code);
            tUserStudentDao.updateByWrapper(sqlWrapper);
        }
    }

    @Override
    public boolean signInByEmail(String email, String pwd, int type) {
        //type:0:学生帐号 1：管理员帐号 2：学院帐号 3：教务帐号
        Object object;
        if(email != null && pwd != null){
            if(type>0){
                SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("a_email",email).eq("a_userPwd",pwd);
                object = tUserStudentDao.findOneByWrapper(sqlWrapper);
            }else{
                SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("a_email",email).eq("a_userPwd",pwd).eq("a_type",type);
                object = tUserAdminDao.findOneByWrapper(sqlWrapper);
            }
        }else{object=null;}
        if(object != null && object != ""){
            return true;
        }
        return false;
    }

    @Override
    public boolean signInByNo(String No, String pwd, int type) {
        //type:0:学生帐号 1：管理员帐号 2：学院帐号 3：教务帐号
        Object object;
        if(No != null && pwd != null){
            if(type>0){
                SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("a_userNo",No).eq("a_userPwd",pwd);
                object = tUserStudentDao.findOneByWrapper(sqlWrapper);
            }else{
                SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("a_userNo",No).eq("a_userPwd",pwd).eq("a_type",type);
                object = tUserAdminDao.findOneByWrapper(sqlWrapper);
            }
        }else{object=null;}
        if(object != null && object != ""){
            return true;
        }
        return false;
    }

    @Override
    public boolean signOut() {
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        List<TUserStudent> students = tUserStudentDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("a_email", email));
        if(students.isEmpty()){
            return true;//邮箱可用
        }
        return false;//邮箱不可用
    }

    @Override
    public boolean checkStudentNo(String No) {
        List<TUserStudent> students = tUserStudentDao.findAllByWrapper(SQLWrapper.instance().select().eq("a_userNo",No));
        if(students.isEmpty()){
            return true;//学号可用
        }
        return false;//学号不可用
    }


}
