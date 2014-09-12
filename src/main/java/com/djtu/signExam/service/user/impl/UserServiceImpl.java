package com.djtu.signExam.service.user.impl;

import com.djtu.signExam.dao.impl.TUserAdminDao;
import com.djtu.signExam.dao.impl.TUserStudentDao;
import com.djtu.signExam.dao.support.IOperators;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by JOECHOW on 2014/9/3.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserStudentDao studentDao;
    @Autowired
    private TUserAdminDao adminDao;

    @Override
    public TUserStudent getStudentInfo(String id) {
        return studentDao.findOneById(id);
    }

    @Override
    public TUserAdmin getAdminInfo(String id) {
        return adminDao.findOneById(id);
    }

    @Override
    public boolean addAdminer(TUserAdmin admin) {
        Object key = adminDao.add(admin);
        if(key!=null && key!=""){
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAdminer(String id) {
        return adminDao.deleteById(id);
    }

    @Override
    public boolean updateAdminer(TUserAdmin admin) {
        return adminDao.update(admin);
    }

    //存在返回true，不存在返回false
    @Override
    public boolean checkEmailExist(String email) {
        if(adminDao.findOneByWrapper(SQLWrapper.instance().selectAll().where().eq("email",email).eq("isDelete",0)) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean resetAdminPassword(String userId, String pwd) {
        return adminDao.updateByWrapper(SQLWrapper.instance().update().set("pwd",pwd).where().eq("ID",userId));
    }

    @Override
    public boolean resetStudentPassword(String userId, String pwd) {
        return studentDao.updateByWrapper(SQLWrapper.instance().update().set("userPwd",pwd).where().eq("ID",userId));
    }

    @Override
    public List<TUserAdmin> getAllAccountByType(int userType) {
        return adminDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("type",userType).eq("isDelete", 0).orderBy(Sortable.inSort("ID", IOperators.SORT.ASC)));
    }

    @Override
    public boolean addAcer() {
        return false;
    }

    @Override
    public boolean removeAcer() {
        return false;
    }

    @Override
    public boolean updateAcer() {
        return false;
    }
}
