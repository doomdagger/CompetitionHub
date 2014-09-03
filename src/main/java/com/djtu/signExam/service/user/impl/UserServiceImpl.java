package com.djtu.signExam.service.user.impl;

import com.djtu.signExam.dao.impl.TUserAdminDao;
import com.djtu.signExam.dao.impl.TUserStudentDao;
import com.djtu.signExam.model.TUserAdmin;
import com.djtu.signExam.model.TUserStudent;
import com.djtu.signExam.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
