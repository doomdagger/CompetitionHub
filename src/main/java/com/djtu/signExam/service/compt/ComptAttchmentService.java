package com.djtu.signExam.service.compt;

import com.djtu.signExam.model.TComptAttchment;

import java.util.List;

/**
 * Created by root on 14-7-24.
 */
public interface ComptAttchmentService {

    public boolean addOne(TComptAttchment tComptAttchment);

    public boolean deleteOneById(String id);

    public List<TComptAttchment> getAllItemsBySkId(String id);
}
