package com.djtu.signExam.service.news.impl;

import com.djtu.signExam.dao.impl.TNewsDao;
import com.djtu.signExam.dao.support.IOperators;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TNews;
import com.djtu.signExam.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by root on 14-7-24.
 */
@Component("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    public TNewsDao tNewsDao;

    @Override
    public List<TNews> getNewsByPage(Pageable pageable) {
        //sort by istop and createtime
        SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().orderBy(Sortable.inSort("is_top", IOperators.SORT.DESC)).orderBy(Sortable.inSort("createtime", IOperators.SORT.DESC)).limit(pageable);
        return tNewsDao.findAllByWrapper(sqlWrapper);
    }

    @Override
    public TNews getNewsById(String id) {
        return tNewsDao.findOneById(id);
    }

    @Override
    public boolean updateNews(TNews tNews) {
        SQLWrapper sqlWrapper = SQLWrapper.instance().update(tNews);
        return tNewsDao.updateByWrapper(sqlWrapper);
    }

    @Override
    public boolean deleteNewsById(String id) {
        return tNewsDao.deleteById(id);
    }

    @Override
    public boolean addNews(TNews tNews) {
        Object key = tNewsDao.add(tNews);
        if(key == null || key == ""){
            return false;
        }
        return true;
    }

    @Override
    public int getPageCount(int pageSize) {
        return tNewsDao.getPageCount(pageSize);
    }
}
