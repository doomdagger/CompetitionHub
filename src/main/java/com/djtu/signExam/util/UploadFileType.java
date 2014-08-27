package com.djtu.signExam.util;

/**
 * Created by JOECHOW on 2014/8/27.
 */
public enum UploadFileType {
    //file type
    FILE("file"),IMG("img"),FLASH("flash"),MEDIA("media");
    private String type;
    private UploadFileType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
