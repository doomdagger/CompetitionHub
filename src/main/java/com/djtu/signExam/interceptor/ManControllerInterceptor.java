package com.djtu.signExam.interceptor;

import com.djtu.signExam.util.SessionConst;
import com.djtu.signExam.util.StringConst;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by root on 14-7-24.
 */
public class ManControllerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Controller in");

        HttpSession httpSession = request.getSession();
        int isLogin = (Integer)httpSession.getAttribute(SessionConst.U_LOGIN);
        HashMap<String,Object> user = (HashMap<String, Object>) httpSession.getAttribute(SessionConst.U_USER);
        if( httpSession == null || isLogin == SessionConst.U_LOGIN_FAIL){
            //如果type是空，或者type=0学生，都不允许进行操作
            response.sendRedirect("/noPermission");
            return false;
        }
        System.out.println("【ManControllerInterceptor】用户过滤开启: 只允许");
        return true;//当返回false，后续的Interceptor和Controller都不会继续执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after");
        super.afterCompletion(request, response, handler, ex);
    }
}
