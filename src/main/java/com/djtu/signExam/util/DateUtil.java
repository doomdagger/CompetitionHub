package com.djtu.signExam.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 14-8-13.
 */
public class DateUtil {

    // get current time
    public static String getDatetime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format( now );
    }
}
