package com.djtu.signExam.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by JOECHOW on 2014/10/12 0012.
 */
public class DownLoadUtil {

    public static void dl(HttpServletRequest request,HttpServletResponse response,String path){
        try{
            //String path=request.getParameter("path");//从页面获取要下载的文件的相对路径
            if(!"".equals(path)){
                path=new String(path.getBytes("ISO-8859-1"),"UTF-8");
                File file=new File(request.getServletContext().getRealPath(UploadConst.VIEWS)+path);//构造要下载的文件
                if(file.exists()){
                    InputStream ins=new FileInputStream(request.getServletContext().getRealPath(UploadConst.VIEWS)+path);//构造一个读取文件的IO流对象
                    BufferedInputStream bins=new BufferedInputStream(ins);//放到缓冲流里面
                    OutputStream outs=response.getOutputStream();//获取文件输出IO流
                    BufferedOutputStream bouts=new BufferedOutputStream(outs);
                    response.setContentType("application/x-download");//设置response内容的类型
                    String[] pieces = path.split("/");
                    path = pieces[pieces.length-1];
                    response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(path, "UTF-8"));//设置头部信息
                    int bytesRead = 0;
                    byte[] buffer = new byte[8192];
                    //开始向网络传输文件流
                    while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                        bouts.write(buffer, 0, bytesRead);
                    }
                    bouts.flush();//这里一定要调用flush()方法
                    ins.close();
                    bins.close();
                    outs.close();
                    bouts.close();
                }else{
                    response.setHeader("text/html","UTF-8");
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("下载的文件不存在");
                    System.out.println("下载的文件不存在");
                }
            }else{
                response.setHeader("text/html","UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("下载文件时参数错误");
                System.out.println("下载文件时参数错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
