package com.djtu.signExam.service.news;

import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.model.TNews;

import java.util.List;

/**
 * Created by root on 14-7-24.
 */
public interface NewsService {

    public List<TNews> getNewsByPage(String userId,Pageable pageable);

    public List<TNews> getAllNewsByPage(Pageable pageable);

    public TNews getNewsById(String id);

    public boolean updateNews(TNews tNews);

    public boolean deleteNewsById(String id);

    public boolean addNews(TNews tNews);

    public int getPageCount(int pageSize);
}
