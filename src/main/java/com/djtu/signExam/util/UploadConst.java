package com.djtu.signExam.util;

import java.io.File;

/**
 * Created by JOECHOW on 2014/8/20.
 */

public class UploadConst {

    /*文件系统保存地址：项目路径+/WEB-INF/views/resources*/
    public static final String PCS_RES_PATH = File.separator+"WEB-INF"+ File.separator+"views"+ File.separator+"resources"+ File.separator;
    /*url访问路径*/
    public static final String URL_RES_PATH = File.separator+"resources"+ File.separator;

    //kindeditor upload path
    public static final String KE_IMG_PATH = "kindedtorUploads"+ File.separator+"img"+File.separator;
    public static final String KE_IMG_THUMBS_PATH = "kindedtorUploads"+File.separator+"thumbs"+File.separator+"img"+File.separator;
    public static final int KE_THUMBS_IMG_WIDTH = 700;
    public static final int KE_THUMBS_IMG_HEIGHT = 350;
}
