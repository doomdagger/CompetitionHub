package com.djtu.signExam.util;

import java.io.File;

/**
 * Created by JOECHOW on 2014/8/20.
 */

public class UploadConst {
	
	//上传文件大小
	public static final int STU_MAX_FILESIZE = 10000000;
	public static final int COMMON_MAX_FILESIZE = 10000000;
	
	
	
	
	
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
    
    
    //上传广告路径
    public static final String ADS_URL_PATH = File.separator+"resources"+ File.separator+"adminUpload"+File.separator+"adsImg"+File.separator;
    public static final String ADS_RES_PATH = File.separator+"WEB-INF"+File.separator+"views"+ADS_URL_PATH;
    public static final int ADS_IMG_HEIGHT = 640;
    public static final int ADS_IMG_WIDTH = 860;
    
    //上传展示图片路径&压缩大小
    public static final String SHOW_URL_PATH = File.separator+"resources"+ File.separator+"adminUpload"+File.separator+"showImg"+File.separator;
    public static final String SHOW_RES_PATH = File.separator+"WEB-INF"+File.separator+"views"+SHOW_URL_PATH;
    public static final int SHOW_COVER_WIDTH = 860;
    public static final int SHOW_COVER_HEIGHT = 640;
    public static final int SHOW_IMG_WIDTH = 1024;
    public static final int SHOW_IMG_HEIGHT = 760;
    
    //上传赛事日程封面路径&压缩大小
    public static final String CAL_URL_PATH = File.separator+"resources"+ File.separator+"adminUpload"+File.separator+"calendarImg"+File.separator;
    public static final String CAL_RES_PATH = File.separator+"WEB-INF"+File.separator+"views"+CAL_URL_PATH;
    public static final int CAL_COVER_WIDTH = 860;
    public static final int CAL_COVER_HEIGHT = 640;
    
}
