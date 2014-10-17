package com.djtu.signExam.util;

import java.io.File;

/**
 * Created by JOECHOW on 2014/8/20.
 */

public class UploadConst {
    public static final String VIEWS = File.separator+"WEB-INF"+File.separator+"views"+File.separator;
    public static final String RESOURCES = File.separator+"WEB-INF"+File.separator+"views"+File.separator+"resources"+File.separator;

    /*文件系统保存地址：项目路径+/WEB-INF/views/resources*/
    public static final String PCS_RES_PATH = File.separator+"WEB-INF"+ File.separator+"views"+ File.separator+"resources"+ File.separator;
    /*url访问路径*/
    public static final String URL_RES_PATH = File.separator+"resources"+ File.separator;

    //kindeditor upload path
    public static final String KE_IMG_PATH = "kindedtorUploads"+ File.separator+"img"+File.separator;
    public static final String KE_IMG_THUMBS_PATH = "kindedtorUploads"+File.separator+"thumbs"+File.separator+"img"+File.separator;
    public static final int KE_THUMBS_IMG_WIDTH = 700;
    public static final int KE_THUMBS_IMG_HEIGHT = 350;

    //util common upload file
    public static final String FILE_URL_PATH = File.separator+"resources"+ File.separator+"UserUpload"+File.separator+"docs"+File.separator;
    public static final String FILE_RES_PATH = File.separator+"WEB-INF"+ File.separator+"views"+ FILE_URL_PATH;

    //student works
    public static final String WORKS_URL_PATH = File.separator+"resources"+File.separator+"stuWorks"+File.separator;
    public static final String WORKS_RES_PATH = File.separator+"WEB-INF"+File.separator+"views"+WORKS_URL_PATH;

}
