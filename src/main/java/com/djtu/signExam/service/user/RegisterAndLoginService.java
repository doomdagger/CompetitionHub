package com.djtu.signExam.service.user;

import com.djtu.signExam.model.TUserStudent;

/**
 * Created by root on 14-7-16.
 */
public interface RegisterAndLoginService {

    public boolean register(TUserStudent tUserStudent);

    public void sendConfirmEmail(String email,String code);//根据验证码及邮箱，验证邮箱，激活帐号

    public boolean signInByEmail(String email,String pwd,int type);

    public boolean signInByNo(String No,String pwd,int type);

    public boolean signOut();

    //check email valid
    public boolean checkEmail(String email);

    //check studentNo
    public boolean checkStudentNo(String No);
}
