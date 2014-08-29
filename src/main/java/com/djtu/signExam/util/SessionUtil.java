package com.djtu.signExam.util;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Created by JOECHOW on 2014/8/29.
 */
public class SessionUtil {

    public static Session getSession(){
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            Session session = (Session) envCtx.lookup("mail/Session");
            return session;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
