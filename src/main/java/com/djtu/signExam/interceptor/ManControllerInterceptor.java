package com.djtu.signExam.interceptor;

import com.djtu.signExam.util.SessionConst;
import com.djtu.signExam.util.StringConst;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joechow on 14-7-24.
 */
public class ManControllerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("【ManControllerInterceptor】用户过滤开启: 只允许管理员");

        HttpSession httpSession = request.getSession();
        if(httpSession==null){
            System.out.println("【ManControllerInterceptor】Session为空-跳转");
            response.sendRedirect("/noPermission");
            return false;
        }
        int isLogin = httpSession.getAttribute(SessionConst.U_LOGIN)==null ? SessionConst.U_LOGIN_FAIL : (Integer)httpSession.getAttribute(SessionConst.U_LOGIN);//从session获取登录状态
        @SuppressWarnings("unchecked")
        HashMap<String,Object> user = httpSession.getAttribute(SessionConst.U_USER)==null ? (new HashMap<String, Object>()) : (HashMap<String, Object>) httpSession.getAttribute(SessionConst.U_USER);//从session中获取user
        Integer userType = user.get(SessionConst.U_USER_TYPE)==null ? 0 : (Integer)user.get(SessionConst.U_USER_TYPE);//获取登录用户权限
        if(isLogin == SessionConst.U_LOGIN_FAIL || userType < 1 ){
            //如果session为空、尚未登录、身份是学生 都将No Permission
            System.out.println("【ManControllerInterceptor】登录状态/学生权限 当前无法通过-跳转");
            response.sendRedirect("/noPermission");
            return false;
        }
        //After first filter

        //选取 /man/需要选取的内容/***
        String currUri = request.getRequestURI();
        int index = currUri.indexOf("/",5);
        currUri = index < 0 ? null : currUri.substring(5,index);
        System.out.println("【ManControllerInterceptor】CurrUri: " + currUri);
        //userType:0:学生帐号 1：管理员帐号 2：学院帐号 3：教务帐号
        HashMap<String, String> uriMap = new HashMap<String, String>();
        uriMap.put("1", "info,news,compt,admin,show");//管理员
        uriMap.put("2", "info,news,compt,show");//学院
        uriMap.put("3", "info,djtu");//教务
        if(currUri!=null){
            String[] tokenArray = uriMap.get(userType.toString()).split(",");
            List<String> tokenList = Arrays.asList(tokenArray);
            if( !tokenList.contains(currUri) ){
                //不在自己权限范围内
                System.out.println("【ManControllerInterceptor】管理员身份 但不在自身权限范围内-跳转");
                response.sendRedirect("/noPermission");
                return false;
            }
        }
        //the final valid
        Boolean isTop = user.get(SessionConst.U_USER_ISTP)==null ? false : (Boolean)user.get(SessionConst.U_USER_ISTP);
        System.out.println("【ManControllerInterceptor】ISTP:"+isTop);
        if(currUri != null && currUri.equals("account") && userType != 2 &&  isTop){
            System.out.println("【ManControllerInterceptor】请求账号管理 当前管理员不是最高权限-跳转");
            response.sendRedirect("/noPermission");
            return false;
        }

        System.out.println("【ManControllerInterceptor】管理员通过");
        return true;//当返回false，后续的Interceptor和Controller都不会继续执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("post");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("after");
        super.afterCompletion(request, response, handler, ex);
    }
}
