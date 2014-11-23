package com.djtu.signExam.util;

import com.djtu.signExam.model.support.AnnotatedModel;
import com.djtu.signExam.model.support.EntityObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by JOECHOW on 2014/9/3.
 */
public class DaoUtil {

    public static void printEntity(EntityObject entityObject){
        AnnotatedModel meta = new AnnotatedModel(entityObject.getClass());
        if(entityObject!=null){
            Method[] methods = entityObject.getClass().getDeclaredMethods();
            System.out.println("表名-{"+meta.getTableName()+"}");
            System.out.println("Model-{"+entityObject.getClass().getSimpleName()+"}");
            for(int i=0;i<methods.length;i++){
                if(methods[i].getName().startsWith("get")){
                    try {
                        Object realFieldName = getRealFieldName(methods[i].getName(),meta);//数据库字段
                        Object mapFieldName = getMapFieldName(methods[i].getName());//Model映射类字段
                        Object value = methods[i].invoke(entityObject, (Object[])null);// 值
                        System.out.println("字段-["+realFieldName+"]"+"\t\t映射-["+mapFieldName+"]"+"\t\t值-["+value+"]");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void printEntity(List<? extends EntityObject> entityList){
        for(EntityObject object : entityList){
            printEntity(object);
        }
    }







    //获取field在数据库中的真实字段名字
    protected static String getRealFieldName(String fieldName,AnnotatedModel meta){
        fieldName = fieldName.substring(3);
        if(fieldName.length()>2 || !"ID".equals(fieldName)){
            fieldName = fieldName.toLowerCase().substring(0, 1)+fieldName.substring(1);
        }
        fieldName = meta.getColumnName(fieldName);
        return fieldName;
    }

    //获取Model类映射的字段名
    protected static String getMapFieldName(String fieldName){
        fieldName = fieldName.substring(3);
        if(fieldName.length()>2 || !"ID".equals(fieldName)){
            fieldName = fieldName.toLowerCase().substring(0, 1)+fieldName.substring(1);
        }
        return fieldName;
    }
}
