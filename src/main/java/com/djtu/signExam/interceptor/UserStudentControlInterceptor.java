package com.djtu.signExam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.djtu.signExam.util.SessionConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.HashMap;

/**
 * <p>
 * 拦截器为开发者提供了3个时间点，以便介入一个请求的生命周期，在这期间，用户可以通过HttpServletResponse改变
 * 响应的方式，这将改变request的原有生命周期，更重要的是，可以增加逻辑来对请求进行额外的控制
 * </p>
 * <p>
 * 拦截器需要在XML配置文件中进行配置，可以使用java config避免，但是无法通过annotation来避免
 * 详情见action-serlvet.xml
 * </p>
 * @author Li He
 *
 */
public class UserStudentControlInterceptor extends HandlerInterceptorAdapter {

	
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        if(httpSession==null){
            System.out.println("【UserStudentControllerInterceptor】Session为空-跳转");
            response.sendRedirect("/noPermission");
            return false;
        }
        try{
            int isLogin = httpSession.getAttribute(SessionConst.U_LOGIN)==null ? SessionConst.U_LOGIN_FAIL : (Integer)httpSession.getAttribute(SessionConst.U_LOGIN);//从session获取登录状态
            @SuppressWarnings("unchecked")
            HashMap<String,Object> user = httpSession.getAttribute(SessionConst.U_USER)==null ? (new HashMap<String, Object>()) : (HashMap<String, Object>) httpSession.getAttribute(SessionConst.U_USER);//从session中获取user
            int userType = user.get(SessionConst.U_USER_TYPE)==null ? 0 : (Integer)user.get(SessionConst.U_USER_TYPE);//获取登录用户权限
            if(isLogin == SessionConst.U_LOGIN_FAIL || userType != 0 ){
                //如果session为空、尚未登录 都将No Permission
                System.out.println("【ManControllerInterceptor】登录状态/非学生权限 当前无法通过-跳转");
                response.sendRedirect("/noPermission");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("【ManControllerInterceptor】程序出错 当前无法通过-跳转");
            response.sendRedirect("/noPermission");
            return false;
        }

		//want to revert the request, please modify return true to return false
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception {
        super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
        super.afterCompletion(request, response, handler, ex);
	}

	
}
