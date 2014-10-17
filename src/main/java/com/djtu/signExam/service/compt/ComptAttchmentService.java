package com.djtu.signExam.service.compt;

import com.djtu.signExam.model.TComptAttachment;

import java.util.List;

/**
 * Created by root on 14-7-24.
 */
public interface ComptAttchmentService {

    public boolean addOne(TComptAttachment tComptAttachment);

    public boolean updateOne(TComptAttachment tComptAttachment);

    public boolean deleteOneById(String id);

    public List<TComptAttachment> getAllItemsBySkId(String id);

    public TComptAttachment getAttachByTeamNo(String teamNo);

    public String isAttachExistInTeamNoAndReturnKey(String teamNo);
}
