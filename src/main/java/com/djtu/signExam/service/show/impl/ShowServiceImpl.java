package com.djtu.signExam.service.show.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djtu.signExam.dao.impl.TShowAlbumDao;
import com.djtu.signExam.dao.impl.TShowDao;
import com.djtu.signExam.dao.support.IOperators.SORT;
import com.djtu.signExam.dao.support.Pageable;
import com.djtu.signExam.dao.support.SQLWrapper;
import com.djtu.signExam.dao.support.Sortable;
import com.djtu.signExam.model.TShow;
import com.djtu.signExam.model.TShowAlbum;
import com.djtu.signExam.service.show.ShowService;
import com.djtu.signExam.util.Util;


@Component("showService")
public class ShowServiceImpl implements ShowService {

	@Autowired
	private TShowDao showDao;
	@Autowired
	private TShowAlbumDao albumDao;
	
	/*
	 * 根据showId获取一个show
	 * @see com.djtu.signExam.service.show.ShowService#getShowById(java.lang.String)
	 */
	@Override
	public TShow getShowById(String showId) {
		return showDao.findOneById(showId);
	}
	
	
	/*
	 * 前端分页访问所有的showList,按时间倒叙
	 * @see com.djtu.signExam.service.show.ShowService#getAllShowInPage(com.djtu.signExam.dao.support.Pageable)
	 */
	@Override
	public List<TShow> getAllShowInPage(Pageable pageable) {
		//set the page count
		SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().orderBy(Sortable.inSort("createtime", SORT.DESC));
		pageable.setPageCount(showDao.getPageCount(pageable.getPageSize(), showDao.getCountByWrapper(sqlWrapper)));
		//get the result
		return showDao.findAllByWrapper(sqlWrapper.limit(pageable));
	}

	/*
	 * 后台管理员自能访问自己发布的show,按时间倒叙
	 * @see com.djtu.signExam.service.show.ShowService#getAllShowByAdminIdInPage(java.lang.String)
	 */
	@Override
	public List<TShow> getAllShowByAdminIdInPage(String adminId,Pageable pageable) {
		//set the page count
		SQLWrapper sqlWrapper = SQLWrapper.instance().selectAll().where().eq("sk_t_admin",adminId).orderBy(Sortable.inSort("createtime", SORT.DESC));
		pageable.setPageCount(showDao.getPageCount(pageable.getPageSize(), showDao.getCountByWrapper(sqlWrapper)));
		//get the result
		return showDao.findAllByWrapper(sqlWrapper.limit(pageable));
	}

	/*
	 * 获取一个show里面的所有照片列表,按上传时间倒叙
	 * @see com.djtu.signExam.service.show.ShowService#getAllPicByShowId(java.lang.String)
	 */
	@Override
	public List<TShowAlbum> getAllPicByShowId(String showId) {
		return albumDao.findAllByWrapper(SQLWrapper.instance().selectAll().where().eq("sk_t_show", showId).orderBy(Sortable.inSort("createtime", SORT.DESC)));
	}

	@Override
	public void addOneShow(TShow show) {
		showDao.add(show);
	}

	@Override
	public void addOnePic(TShowAlbum pic) {
		albumDao.add(pic);
	}

	@Override
	public boolean deleteOneShowById(String showId) {
		return showDao.deleteById(showId);
	}

	@Override
	public boolean deleteOnePicById(String picId) {
		return albumDao.deleteById(picId);
	}

	@Override
	public boolean setTheCover(String showId, String cover) {
		return showDao.updateByWrapper(SQLWrapper.instance().update().set("cover", cover).where().eq("ID", showId));
	}

	/*
	 * 优先一张pic,改变它的createtime
	 * @see com.djtu.signExam.service.show.ShowService#doPrrorityPic(java.lang.String)
	 */
	@Override
	public boolean doPrrorityPic(String picId) {
		return albumDao.updateByWrapper(SQLWrapper.instance().update().set("createtime",Util.dateToString((Date)new Date())).where().eq("ID", picId));
	}

	

}
