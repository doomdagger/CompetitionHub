package com.djtu.signExam.util;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by JOECHOW on 2014/8/29.
 */
public class SessionUtil {

    public static Object getValue(HttpServletRequest request,String key){
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    @SuppressWarnings("unchecked")
    public static Object getValue(HttpServletRequest request,String key1,String key2){
        try{
            HttpSession session = request.getSession();
            HashMap<String,Object> sessionMap;
            sessionMap = (HashMap<String, Object>) session.getAttribute(key1);
            return sessionMap.get(key2);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
