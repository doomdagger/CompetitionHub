package com.djtu.signExam.interceptor;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.djtu.signExam.util.UploadConst;

/*
 * 对包含上传文件的请求进行拦截
 */
public class FileUploadIntercepter extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//request.getMethod();
		System.out.println("拦截请求");
		//如果包含上传文件请求
		if(ServletFileUpload.isMultipartContent(request)){
			System.out.println("拦截文件上传");
			//获取请求中所有的上传文件
			FileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setHeaderEncoding("UTF-8");
	        RequestContext requestContext = new ServletRequestContext(request);
	        List<FileItem> items = upload.parseRequest(requestContext);
	        Iterator<FileItem> itr = items.iterator();
	        System.out.println("文件数量：" + items.size());
	        while(itr.hasNext()){
	        	FileItem item = (FileItem) itr.next();
	            if (!item.isFormField()) {
	                //检查文件大小
	            	System.out.println("文件大小：" + item.getSize());
	                if(item.getSize() > UploadConst.COMMON_MAX_FILESIZE){
	                	response.sendRedirect("/showUploadError");
	                	return false;
	                }
	            }
	        }
		}
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
