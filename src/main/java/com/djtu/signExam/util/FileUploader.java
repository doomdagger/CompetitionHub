package com.djtu.signExam.util;

import org.apache.tomcat.util.http.fileupload.*;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.util.FileUtil;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by JOECHOW on 2014/8/27.
 */
public class FileUploader {

    /**
     *
     * @param request
     * @param response
     * @param savePath：文件系统保存的路径
     * @return:返回新文件名
     * @throws FileUploadException
     */
    public static List<String> uploadFile(HttpServletRequest request, HttpServletResponse response,String savePath) {

        //定义允许上传的文件扩展名
        /*HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("img", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");*/

        //返回文件名的集合
        List<String> fileNameList = new ArrayList<String>();

        //最大文件大小
        long maxSize = 10000000;

        /*创建文件夹*/
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);//创建文件上传对象
        upload.setHeaderEncoding("UTF-8");
        RequestContext requestContext = new ServletRequestContext(request);
        List items = null;
        try {
            items = upload.parseRequest(requestContext);
        } catch (FileUploadException e) {
            e.printStackTrace();
            System.out.println("获取文件出错");
            return null;
        }
        Iterator itr = items.iterator();
        //遍历提交的上传文件
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            String fileName = item.getName();//获取文件名
            long fileSize = item.getSize();//获取文件大小
            if (!item.isFormField()) {
                //检查文件大小
                if(fileSize > maxSize){
                    //("上传文件大小超过限制。");
                    System.out.println("大小问题");
                }
                //检查扩展名
                /*String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if(!Arrays.<String>asList(extMap.get(type).split(",")).contains(fileExt)){
                    //("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
                    System.out.println("扩展名问题");
                    return false;
                }*/
                //SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                //String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                try{
                    fileNameList.add(fileName);//文件名-添加到返回集合
                    File uploadedFile = new File(savePath, fileName);
                    item.write(uploadedFile);//写入文件
                    return fileNameList;
                }catch(Exception e){
                    System.out.println("上传出现问题");
                }
            }
        }
        System.out.println("上传列队为空");
        return null;
    }
}
