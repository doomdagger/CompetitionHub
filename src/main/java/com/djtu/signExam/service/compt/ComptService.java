package com.djtu.signExam.service.compt;

import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TCompt;

import java.util.List;

/**
 * Created by root on 14-7-24.
 */
public interface ComptService {

    //基本操作
    public List<TCompt> getComByPage(String userId,Pageable pageable);

    public List<TCompt> getAllComByPage(Pageable pageable);
    
    public List<TCompt> getAllIndexListByPage(Pageable pageable);

    public TCompt getComptById(String id);

    public boolean updateCompt(TCompt tCompt);

    public boolean deleteComptById(String id);

    public Integer addCompt(TCompt tCompt);
    
    public boolean setToTop(String id,boolean isTop);


    //流程操作
    //状态 status ： 0等待审核(通过/拒绝) 1通过审核(报名中) 2未通过审核(等重新发布) 3报名结束(比赛进行中) 4比赛结束(确认结果/成绩录入)
    public boolean updateStatus(String id,int status);

    //tools
    public int getPageCount(int pageSize);
}
