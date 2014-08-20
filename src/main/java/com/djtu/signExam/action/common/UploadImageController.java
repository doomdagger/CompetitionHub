package com.djtu.signExam.action.common;

import com.djtu.signExam.util.CompressPicUtils;
import com.djtu.signExam.util.UploadConst;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by JOECHOW on 2014/8/20.
 */

@Controller
@RequestMapping("/upload")
public class UploadImageController {

    @RequestMapping("/uploadImage")
    public @ResponseBody String uploadImage(HttpServletRequest request, HttpServletResponse response) throws FileUploadException {
        //文件保存目录路径(FileSystem)
        String savePath = request.getServletContext().getRealPath(UploadConst.PCS_RES_PATH+UploadConst.KE_IMG_PATH);
        String saveThumbsPath = request.getServletContext().getRealPath(UploadConst.PCS_RES_PATH+UploadConst.KE_IMG_THUMBS_PATH);

        //文件保存目录URL(DB)
        String saveUrl  = UploadConst.URL_RES_PATH +UploadConst.KE_IMG_PATH;
        String saveThumbsUrl =  UploadConst.URL_RES_PATH +UploadConst.KE_IMG_THUMBS_PATH;

        /*test*/
        /*System.out.println("savePath: " + savePath);
        System.out.println("saveUrl: "+saveUrl);*/

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        //最大文件大小
        long maxSize = 1000000;

        response.setContentType("text/html; charset=UTF-8");

        if(!ServletFileUpload.isMultipartContent(request)){
            //out.println();
            return getError("请选择文件。");
        }
        //检查目录
        /*File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            //out.println();
            return getError("上传目录不存在。");
        }*/
        //检查目录写权限
        /*if(!uploadDir.canWrite()){
            //out.println();
            return getError("上传目录没有写权限。");
        }*/

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        /*if(!extMap.containsKey(dirName)){
            //out.println();
            return getError("目录名不正确。");
        }
        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";*/

        /*创建文件夹*/
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        File saveThumbsDirFile = new File(saveThumbsPath);
        if(!saveThumbsDirFile.exists()){
            saveThumbsDirFile.mkdirs();
        }
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }*/

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        RequestContext requestContext = new ServletRequestContext(request);
        List items = upload.parseRequest(requestContext);
        Iterator itr = items.iterator();
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            String fileName = item.getName();
            long fileSize = item.getSize();
            if (!item.isFormField()) {
                //检查文件大小
                if(item.getSize() > maxSize){
                    //out.println();
                    return getError("上传文件大小超过限制。");
                }
                //检查扩展名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
                    //out.println();
                    return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                try{
                    File uploadedFile = new File(savePath, newFileName);
                    //写入图片
                    item.write(uploadedFile);
                    //double thumbsHeight = getHeight(item.)
                    //生成缩略图
                    //CompressPicUtils.compressPic(item.getInputStream(),new File(saveThumbsPath,newFileName),UploadConst.KE_THUMBS_IMG_WIDTH,UploadConst.KE_THUMBS_IMG_HEIGHT,true);
                }catch(Exception e){
                    //out.println();
                    return getError("上传文件失败。");
                }

                JSONObject obj = new JSONObject();
                obj.put("error", 0);
                obj.put("url", saveUrl + newFileName);
                return obj.toString();
            }
        }
        return getError("Operation missing");
    }
    //tools
    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toString();
    }
}
