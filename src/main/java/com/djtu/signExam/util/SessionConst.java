package com.djtu.signExam.util;

/**
 * Created by root on 14-7-24.
 */
public class SessionConst {


    public final static String U_LOGIN = "U_LOGIN";
    public final static int U_LOGIN_SUCC = 1;
    public final static int U_LOGIN_FAIL = 0;
    public final static String U_USER = "CUR";
    public final static String U_USER_TYPE = "CUR_TYPE";
    public final static String U_USER_NAME = "CUR_NAME";
    public final static String U_USER_LINK = "CUR_LINK";//用户ID
    public final static String U_USER_ISTP = "CUR_ISTP";//最高权限

    public final static String REG_VALIDCODE = "VC";
    public final static int REG_SESSION_INTERVAL = 10*60;//10分钟
    //添加、编辑 管理员用户
    public final static String ADD_ACCOUNT_CODE = "AAC";
    public final static String EDIT_ACCOUNT_CODE = "EAC";
    //修改密码的session 验证码
    public final static String MODIFY_PASSWORD = "MDPD";

}
